<template>
	<div>

	</div>
</template>

<script>

import router from '@/router'
import { useUserStore } from '@/store/user'

export default {

	setup() {
		const userStore = useUserStore()
		return { userStore }
  	},

	data() {
		return {
			success: false,
			user: {},
		}
	},


	methods: {


	},
   
	mounted() {
		this.userStore.logoutUser()
		router.push({path: "/message/6"})
	},
	
	computed: {

	}

}
</script>

<style scoped>

</style>