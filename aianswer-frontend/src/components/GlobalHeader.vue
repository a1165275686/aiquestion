<template>
  <a-row id="globalHeader" align="center" :wrap="false">
    <a-col flex="auto">
      <a-menu
        mode="horizontal"
        :selected-keys="selectedKeys"
        @menu-item-click="doMenuClick"
      >
        <a-menu-item
          key="0"
          :style="{ padding: 0, marginRight: '38px' }"
          disabled
        >
          <div class="titleBar">
            <img class="logo" src="../assets/logo.png" />
            <div class="title">乐趣AI</div>
          </div>
        </a-menu-item>
        <a-menu-item v-for="item in visibleRoutes" :key="item.path">
          {{ item.name }}
        </a-menu-item>
      </a-menu>
    </a-col>
    <a-col flex="100px">
      <div v-if="loginUserStore.loginUser.id">
        {{ loginUserStore.loginUser.userName ?? "无名" }}
        <a-button type="primary" @click="logout">退出</a-button>
      </div>
      <div v-else>
        <a-button type="primary" href="/user/login">登录</a-button>
      </div>
    </a-col>
  </a-row>
</template>

<script setup lang="ts">
import { routes } from "@/router/routes";
import { useRouter } from "vue-router";
import { computed, ref } from "vue";
import { useLoginUserStore } from "@/store/userStore";
import checkAccess from "@/access/checkAccess";
import message from "@arco-design/web-vue/es/message";

const loginUserStore = useLoginUserStore();
const router = useRouter();
// 当前选中的菜单项
const selectedKeys = ref(["/"]);
// 路由跳转时，自动更新选中的菜单项
router.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
});

// 展示在菜单栏的路由数组
const visibleRoutes = computed(() => {
  return routes.filter((item) => {
    if (item.meta?.hideInMenu) {
      return false;
    }
    // 根据权限过滤菜单
    if (!checkAccess(loginUserStore.loginUser, item.meta?.access as string)) {
      return false;
    }
    return true;
  });
});

// 点击菜单跳转到对应页面
const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};
/**
 * 退出
 */
const logout = async () => {
  // 在这里执行退出操作，例如清除会话中的用户信息
  loginUserStore.clearLoginUser(); // 假设退出成功，将登录状态设置为 false
  message.success("退出成功");
  router.push({
    path: "/user/login",
    replace: true
  });
};
</script>
<style scoped>
#globalHeader {
}

.titleBar {
  display: flex;
  align-items: center;
}

.title {
  margin-left: 16px;
  color: black;
}

.logo {
  height: 48px;
}
</style>
