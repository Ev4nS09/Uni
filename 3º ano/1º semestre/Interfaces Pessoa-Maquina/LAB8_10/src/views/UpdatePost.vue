<template>
    <div>
        <Menu />
		<form @submit.prevent="handleSubmit" >
        	<div class="input-group container-sm pt-4">
            	<textarea class="form-control bg-black text-white"  v-model="post.content" placeholder="What's on your mind?" name="content">{{this.post.content}}</textarea>
            	<button type="submit" class = "btn btn-outline-light d-inline">Update</button>
        	</div>
    	</form>
    </div>

</template>

<script>
import router from '@/router'
import Menu from '@/components/Menu.vue'

import { useUserStore } from '@/store/user'
import { useMicropostsStore } from '@/store/microposts'

export default { 

	setup() {
		const userStore = useUserStore()
		const micropostsStore = useMicropostsStore()
		return { userStore, micropostsStore}
  	},

    components: {
        Menu
	},	
	data() {
      return {
		submitting: false,
		error: false,
		post: this.micropostsStore.getMicropost(this.$route.params.id),
		user: this.userStore.getUser,
		userLoggedIn: Object.keys(this.userStore.getUser).length !== 0,
      }
    },

	mounted() {
		if(this.post.user_id !== this.user.id || !this.userLoggedIn)
			router.push({path:"/"})
	},
	
	methods: {
		async handleSubmit(){
			await this.micropostsStore.updateMicropostDB(this)
			router.push({path:"/message/1"})
		}
	},

	computed: {

	},
	directives: {

	},
}
</script>

<style scoped>



</style>