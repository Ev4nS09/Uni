<template>
<div>
	<h1>{{ message }}</h1>
</div>
</template>

<script>
export default {
    data() {
		return {
            message: '',
		}
	},   
    mounted() {
        this.message = localStorage.getItem('message')
        var timeleft = 2;
        var downloadTimer = setInterval(() => {
        if(timeleft <= 0){
            clearInterval(downloadTimer);
            this.$router.push('/products')
        }
        timeleft -= 1;
        }, 1000);
	},

}
</script>

<style scoped>
</style>



