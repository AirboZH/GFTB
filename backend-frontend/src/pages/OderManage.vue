<template>
oderman
</template>

<script>
export default {
  name: "OderManage",
  mounted() {
    if(!localStorage.getItem('token')) {
      this.$router.push('/account')
      return;
    }
  }
}
</script>

<style scoped>

</style>