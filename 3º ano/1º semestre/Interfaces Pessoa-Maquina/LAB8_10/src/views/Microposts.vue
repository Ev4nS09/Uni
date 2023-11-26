<template >
	<div>
		<Menu />	
			<div v-if = "userLoggedIn" class = "container-md col d-flex justify-content-start align-items-center pt-5">
				<router-link to = "/post"><img src="@\assets\images\post.png" height="45" width="45"></router-link>
				<router-link to = "/post" class = "text-decoration-none text-white pt-2"><h4>Post</h4></router-link>
      		</div>
			<div v-for="micropost in microposts" :key="micropost.id" class="container">
				<div style="word-wrap: break-word" class = "container-xl my-5 border border-1 p-1 rounded-2"> 
      				<div class = "col d-flex justify-content-start align-items-center p-2">
        				<img class = "border-2 border-light rounded-2" src="@\assets\images\silly_cat.jpg" height="60" width="60">
        				<h1 class="ps-2 text-white">{{micropost.name}}</h1>
        				<h5 class="ps-3 pt-2 text-white">created at {{micropost.created_at}}</h5>
						<h6 class="ps-3 pt-2 text-white opacity-50">updated at {{micropost.updated_at}}</h6>
						<div v-if="micropost.user_id === userStore.getUser.id" class="col d-flex container-sm justify-content-end align-items-right mb-2">
            				<button class="btn btn-sm btn-outline-light d-inline mx-1 float-right" @click = "goToUpdatePost(micropost.id)" >Update</button>
         				</div>
						 <div v-else-if="userLoggedIn && (micropost.user_id !== userStore.getUser.id)" class="col d-flex container-sm justify-content-end align-items-right mb-2">
            				<button class="btn btn-sm btn-outline-light d-inline mx-1 float-right" @click = "goToPostComment(micropost.id)" >Comment</button>
         				</div>
					</div>
					<div style="word-wrap: break-word" class = "justify-content-start align-items-center p-2">
        				<h5 class ="text-white">{{micropost.content}}</h5>
      				</div>

					<button @click = showComments(micropost.id) class ="btn btn-outline-light d-inline mx-1">Show Comments</button>
					<div>
						<div v-for="comment in comments" class="container">
							<div v-if = "comment.micropost_id == micropost.id" style="word-wrap: break-word" class = "container-xl my-5 border border-1 p-1 rounded-2"> 
								<div class = "col d-flex justify-content-start align-items-center p-2">
									<img class = "border-2 border-light rounded-2" src="@\assets\images\silly_cat.jpg" height="60" width="60">
									<h1 class="ps-2 text-white">{{comment.user_name}}</h1>
									<h5 class="ps-3 pt-2 text-white">created at {{comment.created_at}}</h5>
								</div>
								<div style="word-wrap: break-word" class = "justify-content-start align-items-center p-2">
        								<h5 class ="text-white">{{comment.content}}</h5>
      							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
</template>
<script>
import Menu from '@/components/Menu.vue'
import router from '@/router'

import { useMicropostsStore } from '@/store/microposts'
import { useUserStore } from '@/store/user'
import { useCommentsStore } from '@/store/comments'

export default {

	setup() {
		const userStore = useUserStore()
		const micropostsStore = useMicropostsStore()
		const commentsStore = useCommentsStore()
		micropostsStore.getMicropostsDB()
		commentsStore.getCommentsDB()
		return { userStore, commentsStore, micropostsStore }
  	},

	components: {
        Menu
	},
	data() {
		return {
			microposts: this.micropostsStore.getMicroposts,
			comments: this.commentsStore.getComments,
			user: {
				id: '', 
				name: '', 
				email: '', 
				session_id: ''
			},
			show: '',
			userLoggedIn: Object.keys(this.userStore.getUser).length !== 0,
		}
	},
	mounted() {

	},
	methods: {
		showComments(postID){
			this.comments = this.commentsStore.getPostComments(postID)
		},
		goToUpdatePost(postID){
			router.push({path:"/updatepost/" + postID})
		},
		goToPostComment(postID){
			router.push({path:"/commentpost/" + postID})
		}
	},
	computed: {

	}
}
</script>

<style scoped>

</style>