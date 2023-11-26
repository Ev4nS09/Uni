<template>
		<Menu />
    	<form @submit.prevent="handleSubmit" >
        	<div class="input-group container-sm pt-4">
            	<textarea class="form-control bg-black text-white"  v-model="post.content" placeholder="What's on your mind?" name="content"></textarea>
            	<button type="submit" class = "btn btn-outline-light d-inline">Post</button>
        	</div>
    	</form>
</template>

<script>
import Menu from '@/components/Menu.vue'
import router from '@/router'


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
        post: {
            content: '',
        },
		user: this.userStore.getUser,
		userLoggedIn: Object.keys(this.userStore.getUser).length !== 0,
      }
    },

	mounted() {
        if(!this.userLoggedIn)
            router.push({path:"/"}) 
	},
	
	methods: {
		async handleSubmit(){
            await this.micropostsStore.addMicropostDB(this)
			router.push({path:"/message/3"}) 
        }
	},
	
	computed: {

	},
	directives: {

	},
}
</script>