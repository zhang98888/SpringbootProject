<template>
  <div>
    <Header></Header>
  </div>
  <div>
    <router-view />
  </div>
</template>

<script>
import Header from './components/Header.vue'

export default {
  components: { Header },
  name: 'app'
}
</script>

<style></style>
