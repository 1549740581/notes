/* 分离模板 */
<template>
  <div>
    <h2 class="title">{{ message }}</h2>
    <button @click="onClick">点击</button>
  </div>
</template>

/* 分离脚本 */
<script>
export default {
  name: "App",
  data() {
    return {
      message: "msg"
    };
  },
  methods: {
    onClick() {
      console.log("vue------------");
    }
  }
};
</script>

/* 分离样式 */
<style scoped>
.title {
  color: blue;
}
</style>
