<template >
  <div class="background-image d-flex align-items-center justify-content-center" id="app">
    <div class="container text-center">
      <h2 class="text-white">Order your favourite banger</h2>
      <router-link to="/menu" class="btn btn-lg btn-outline-light d-inline mx-1" role="button">Order</router-link>
    </div>
  </div>
</template>

<script>
</script>

<style>
.background-image{
  background-image: url('./images/dre_dogg.jpg');
  background-size: cover;
  background-position: center;
  height: 100vh;
  width: 100%;
  position: relative;
}
</style>