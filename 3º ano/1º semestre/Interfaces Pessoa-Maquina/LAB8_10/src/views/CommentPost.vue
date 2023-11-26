<template>
		<Menu />
    	<form @submit.prevent="handleSubmit" >
        	<div class="input-group container-sm pt-4">
            	<textarea class="form-control bg-black text-white"  v-model="comment.content" placeholder="What's on your mind?" name="content"></textarea>
            	<button type="submit" class = "btn btn-outline-light d-inline">Comment</button>
        	</div>
    	</form>

</template>

<script>
import Menu from '@/components/Menu.vue'
import router from '@/router'

import { useCommentsStore } from '@/store/comments'
import { useMicropostsStore } from '@/store/microposts'
import { useUserStore } from '@/store/user'

export default {  

	setup() {
		const userStore = useUserStore()
        const micropostsStore = useMicropostsStore()
		const commentsStore = useCommentsStore()
		return { userStore, micropostsStore, commentsStore }
  	},

    components: {
        Menu
	},	
	data() {
      return {
		submitting: false,
		error: false,
        post: this.micropostsStore.getMicropost(this.$route.params.id),
        comment: {
            content: '',                             
        },
		user: this.userStore.getUser,
        userLoggedIn: Object.keys(this.userStore.getUser).length !== 0,
      }
    },

	mounted() {
        if(!this.userLoggedIn)
            router.push({path:"/"}) 
		if(this.user.id === this.post.user_id)
			router.push({path:"/"}) 
	},
	
	methods: {
		async handleSubmit(){
            await this.commentsStore.addCommentDB(this)
			router.push({path:"/message/7"}) 
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