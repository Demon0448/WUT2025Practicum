<template>
  <div class="sign-statistics">
    <div class="header-section">
      <h1 class="chart-title">
        <el-icon class="title-icon"><TrendCharts /></el-icon>
        近五日签到情况统计视图
      </h1>
      <el-button
        @click="refreshChart"
        :loading="loading"
        type="primary"
        class="refresh-btn"
      >
        <el-icon><Refresh /></el-icon>
        刷新数据
      </el-button>
      <!-- 新增 load 按钮 -->
      <el-button
        @click="handleLoad"
        type="default"
        class="load-btn"
      >
        load
      </el-button>
    </div>

    <el-card class="chart-card" v-loading="loading" element-loading-text="正在加载图表数据...">
      <div id="main" ref="chartContainer" class="chart-container"></div>

      <!-- 加载状态遮罩 -->
      <div v-if="loading" class="loading-overlay">
        <div class="loading-content">
          <el-icon class="loading-icon"><Loading /></el-icon>
          <p class="loading-text">正在获取统计数据...</p>
        </div>
      </div>

      <!-- 空数据状态 -->
      <div v-if="!loading && hasError" class="empty-state">
        <el-icon class="empty-icon"><DocumentRemove /></el-icon>
        <p class="empty-text">暂无统计数据</p>
        <el-button @click="refreshChart" type="primary">重新获取</el-button>
      </div>
    </el-card>

    <!-- 新增分页组件 -->
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :total="total"
      layout="prev, pager, next, jumper, sizes"
      :page-sizes="[5, 10, 20, 50]"
      background
      class="pagination"
      @current-change="handlePageChange"
      @size-change="handleSizeChange"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, nextTick } from 'vue'
import { ElMessage } from 'element-plus'
import { TrendCharts, Refresh, Loading, DocumentRemove } from '@element-plus/icons-vue'
import axios from 'axios'
import * as echarts from 'echarts'

const chartContainer = ref<HTMLElement>()
const loading = ref(false)
const hasError = ref(false)
let myChart: echarts.ECharts | null = null

// 新增分页相关状态
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(1500)

const initChart = () => {
  if (chartContainer.value) {
    myChart = echarts.init(chartContainer.value)

    // 设置默认图表配置
    const defaultOption = {
      title: {
        text: '近五日签到图',
        textStyle: {
          color: '#333'
        }
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      legend: {
        data: ['已签到人数', '未签到人数', '需签到总人数']
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
      },
      series: [
        {
          name: '已签到人数',
          type: 'bar',
          data: [],
          itemStyle: {
            color: '#67C23A'
          }
        },
        {
          name: '未签到人数',
          type: 'bar',
          data: [],
          itemStyle: {
            color: '#F56C6C'
          }
        },
        {
          name: '需签到总人数',
          type: 'bar',
          data: [],
          itemStyle: {
            color: '#409EFF'
          }
        }
      ]
    }

    myChart.setOption(defaultOption)

    // 监听窗口大小变化
    window.addEventListener('resize', () => {
      myChart?.resize()
    })
  }
}

// 加载图表数据
const loadChartData = async () => {
  loading.value = true;
  hasError.value = false;

  try {
    const response = await axios.get('/api/v1/admin/attendance/statistics/chart', {
      params: {
        currentPage: currentPage.value,
        pageSize: pageSize.value
      }
    });

    if (response.data) {
      const dates = response.data.data || []; // 日期数组
      const signedData = response.data.data1 || []; // 已签到人数
      const unsignedData = response.data.data2 || []; // 未签到人数
      const totalData = response.data.data3 || []; // 需签到总人数

      // 更新分页总数
      total.value = response.data.total || 1500;

      // 检查数据是否有效
      if (dates.length === 0) {
        console.warn('日期数据为空');
        hasError.value = true;
        ElMessage.warning('暂无统计数据，请检查数据源');
        return;
      }

      // 确保图表实例存在
      if (!myChart) {
        initChart(); // 初始化图表
      }

      myChart?.setOption({
        xAxis: { data: dates },
        series: [
          { data: signedData },
          { data: unsignedData },
          { data: totalData }
        ]
      });
    } else {
      hasError.value = true;
      ElMessage.error('获取统计数据失败');
    }
  } catch (error) {
    console.error('获取统计数据失败:', error);
    hasError.value = true;
    ElMessage.error('获取统计数据失败，请稍后重试');
  } finally {
    loading.value = false;
  }
}

// 刷新图表数据
const refreshChart = async () => {
  currentPage.value = 1
  await loadChartData()
}

// 处理分页变化
const handlePageChange = async (newPage: number) => {
  currentPage.value = newPage
  await loadChartData()
}

// 处理每页条数变化
const handleSizeChange = async (newPageSize: number) => {
  pageSize.value = newPageSize;
  await loadChartData();
};

// 新增 handleLoad 方法
const handleLoad = async () => {
  try {
    const response = await axios.get('/api/v1/admin/attendance/load');
    console.log('Load data:', response.data);
    ElMessage.success('数据加载成功');

    // 调用图表数据加载方法以同步更新图表
    await loadChartData();
  } catch (error) {
    console.error('数据加载失败:', error);
    ElMessage.error('数据加载失败');
  }
};

onMounted(async () => {
  await nextTick()
  initChart()
  loadChartData()
})
</script>

<style scoped>
.sign-statistics {
  padding: 24px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-height: 100vh;
}

.header-section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
  background: rgba(255, 255, 255, 0.95);
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.chart-title {
  display: flex;
  align-items: center;
  font-size: 24px;
  font-weight: 600;
  color: #303133;
  margin: 0;
}

.title-icon {
  margin-right: 8px;
  color: #409EFF;
}

.refresh-btn {
  margin-left: 16px;
}

.load-btn {
  margin-left: 16px;
}

.chart-card {
  position: relative;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.chart-container {
  width: 100%;
  height: 600px;
  min-height: 400px;
}

.loading-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(255, 255, 255, 0.9);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 10;
}

.loading-content {
  text-align: center;
}

.loading-icon {
  font-size: 48px;
  color: #409EFF;
  animation: rotate 2s linear infinite;
  margin-bottom: 16px;
}

.loading-text {
  font-size: 16px;
  color: #606266;
  margin: 0;
}

.empty-state {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

.empty-icon {
  font-size: 64px;
  color: #C0C4CC;
  margin-bottom: 16px;
}

.empty-text {
  font-size: 16px;
  color: #909399;
  margin-bottom: 16px;
}

.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}

@keyframes rotate {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>
