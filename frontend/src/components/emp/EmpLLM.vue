<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount, nextTick } from 'vue'
import { useRoute } from 'vue-router'
import { ElMessage, ElInput, ElButton } from 'element-plus'
import DS from '@/assets/deepseek.png'
import EyeGif from '@/assets/WEBSITE.png'
import UserLogo from '@/assets/用户.png'

const route = useRoute()

const text = ref('')
const isSending = ref(false)
const messageList = ref([
  {
    name: 'DeepSeek',
    message: '我是小智助手，有什么问题尽管问我吧！',
  },
])
const apiKey = 'sk-08f86350ab6d408b9845c1f8515f2e17' // 实际API Key
const apiUrl = 'https://api.deepseek.com/chat/completions'
const abortController = ref(null)

const filterMarkdown = (text) => {
  // 替换标题（###, ##, #）
  text = text.replace(/^#+\s*(.*?)\s*#*$/gm, '$1\n')

  // 替换无序列表项（*, -, +）
  text = text.replace(/^([*+-])\s+/gm, '• ')

  // 替换有序列表项（1., 2. 等）
  text = text.replace(/^(\d+\.)\s+/gm, '$1 ')

  // 移除代码块
  text = text.replace(/```[\s\S]*?```/g, '')

  // 移除行内代码
  text = text.replace(/`[^`]*`/g, '')

  // 移除粗体/斜体标记（保留内容）
  text = text.replace(/(\*\*|__)(.*?)\1/g, '$2')
  text = text.replace(/([*_])(.*?)\1/g, '$2')

  // 移除链接和图片（保留描述文字）
  text = text.replace(/!?\[([^\]]*)]\([^)]*\)/g, '$1')

  // 移除引用标记
  text = text.replace(/^>\s*/gm, '')

  // 移除分隔线
  text = text.replace(/^[-*]{3,}\s*$/gm, '')

  // 移除表格标记
  text = text.replace(/^\|.+\|$/gm, '')
  text = text.replace(/^[-:|]+\s*$/gm, '')

  // 规范化换行（多个空行合并为一个）
  text = text.replace(/\n{3,}/g, '\n\n')

  // 移除行首行尾空白
  text = text.trim()

  return text
}

const sendMessage = async () => {
  if (text.value.trim().length === 0) {
    ElMessage.error('请勿发送空消息')
    return
  }

  isSending.value = true

  // 添加用户消息到列表
  messageList.value.push({
    name: 'User',
    message: text.value,
  })

  // 添加AI的空白消息占位
  messageList.value.push({
    name: 'DeepSeek',
    message: '',
  })

  const userMessage = text.value
  text.value = '' // 清空输入框

  try {
    // 创建AbortController以便可以取消请求
    abortController.value = new AbortController()

    const response = await fetch(apiUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${apiKey}`,
      },
      body: JSON.stringify({
        model: 'deepseek-chat',
        messages: [
          {
            role: 'user',
            content: userMessage,
          },
        ],
        stream: true, // 启用流式响应
      }),
      signal: abortController.value.signal,
    })

    if (!response.ok) {
      ElMessage.error(`API请求失败: ${response.status}`)
      return
    }

    // 处理流式响应
    const reader = response.body.getReader()
    const decoder = new TextDecoder()
    const aiMessageIndex = messageList.value.length - 1

    // eslint-disable-next-line no-constant-condition
    while (true) {
      const { done, value } = await reader.read()
      if (done) break

      const chunk = decoder.decode(value)
      const lines = chunk.split('\n').filter((line) => line.trim() !== '')

      for (const line of lines) {
        if (line.startsWith('data: ')) {
          const data = line.replace('data: ', '')
          if (data === '[DONE]') continue

          try {
            const parsedData = JSON.parse(data)
            if (parsedData.choices && parsedData.choices[0].delta.content) {
              // 过滤Markdown格式后再添加到消息
              const filteredContent = filterMarkdown(parsedData.choices[0].delta.content)
              messageList.value[aiMessageIndex].message += filteredContent

              // 自动滚动到底部
              await nextTick(() => {
                const messageBox = document.querySelector('.messageBox')
                if (messageBox) {
                  messageBox.scrollTop = messageBox.scrollHeight
                }
              })
            }
          } catch (e) {
            ElMessage.error('解析JSON出错: ' + e.message)
          }
        }
      }
    }
  } catch (error) {
    if (error.name === 'AbortError') {
      ElMessage.info('请求已取消')
    } else {
      ElMessage.error('请求出错: ' + error.message)
      // 如果出错，移除AI的空白消息占位
      messageList.value.pop()
    }
  } finally {
    isSending.value = false
    abortController.value = null
  }
}

onBeforeUnmount(() => {
  // 组件销毁前取消未完成的请求
  if (abortController.value) {
    abortController.value.abort()
  }
})
</script>

<template>
  <main class="main">
    <div class="title">
      <h2 style="margin: 5px; text-align: center">
        DeepSeek智能服务
        <img :src="DS" alt="" style="width: 10%" />
      </h2>
    </div>
    <div class="chatBox">
      <div class="messageBox">
        <div class="messageList">
          <div
            v-for="(msg, index) in messageList"
            :key="index"
            :class="['message', msg.name === 'DeepSeek' ? 'left' : 'right']"
          >
            <!-- DeepSeek 的消息 -->
            <template v-if="msg.name === 'DeepSeek'">
              <img class="avatar" :src="EyeGif" alt="DeepSeek" />
              <div class="content">{{ msg.message }}</div>
            </template>
            <!-- 用户的消息 -->
            <template v-else>
              <div class="content">{{ msg.message }}</div>
              <img class="avatar" :src="UserLogo" alt="User" />
            </template>
          </div>
        </div>
      </div>
      <div class="gap"></div>
      <div class="footer">
        <ElInput
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 4 }"
          placeholder="快来问我吧！"
          maxlength="100"
          show-word-limit
          @keyup.enter="sendMessage"
          style="width: 98%; align-self: center"
          v-model="text"
        />
        <ElButton
          @click="sendMessage"
          :disabled="isSending"
          style="float: right; margin: 1%; background: #00c0a6; color: white; font-weight: bolder"
        >
          点击发送
        </ElButton>
      </div>
    </div>
  </main>
</template>

<style scoped>
.main {
  height: 100%;
  display: flex;
  flex-direction: column;
  min-height: 0;
}

.title {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  margin-bottom: 10px;
}

.chatBox {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  min-height: 0;
}

.footer {
  padding: 10px;
  background: #fff;
  border-top: 1px solid #eee;
  flex-shrink: 0;
}

.gap {
  margin: 5px;
}

.messageBox {
  flex: 1;
  min-height: 0;
  overflow-y: auto;
  margin: 2px;
  padding: 10px;
}

.messageList {
  display: flex;
  flex-direction: column;
  gap: 12px; /* 增加消息间距 */
}

.message {
  display: flex;
  margin-bottom: 10px;
}

/* 大模型消息 - 左对齐 */
.message.left {
  justify-content: flex-start;
}

/* 用户消息 - 右对齐 */
.message.right {
  justify-content: flex-end;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin: 0 10px;
  flex-shrink: 0;
}

.content {
  max-width: 70%;
  padding: 12px 15px;
  border-radius: 18px;
  line-height: 1.4;
  word-break: break-word;
}

/* 大模型消息样式 */
.message.left .content {
  background-color: #e0f7fa;
  border-top-left-radius: 4px; /* 小细节调整 */
}

/* 用户消息样式 */
.message.right .content {
  background-color: #00c0a6;
  color: white;
  border-top-right-radius: 4px;
}

/* 调整消息顺序 */
.message.right {
  flex-direction: row-reverse; /* 用户消息：头像在右 */
}
</style>
