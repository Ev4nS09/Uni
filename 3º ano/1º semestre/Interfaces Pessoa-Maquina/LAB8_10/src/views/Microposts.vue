<template >
	<div>
		<Menu />	
			<div v-for="micropost in methods.microposts" :key="micropost.id" class="container">
				<div style="word-wrap: break-word" class = "container-xl my-5 border border-1 p-1 rounded-2"> 
      				<div class = "col d-flex justify-content-start align-items-center p-2">
        				<img class = "border-2 border-light rounded-2" src="images\silly_cat.jpg" height="60" width="60">
        				<h1 class="ps-2 text-white">{{micropost.name}}</h1>
        				<h5 class="ps-3 pt-2 text-white">created at {{micropost.created_at}}</h5>
						<h6 class="ps-3 pt-2 text-white opacity-50">updated at {{micropost.updated_at}}</h6>
					</div>
				</div>
			</div>
		<Footer />
	</div>
</template>

<script>
import Footer from '@/components/Footer.vue'
import Menu from '@/components/Menu.vue'

import { useMicropostsStore } from '@/store/microposts'
import { useUserStore } from '@/store/user'
import { useCommentsStore } from '@/store/comments'

export default {

	setup() {
		const userStore = useUserStore()
		const micropostsStore = useMicropostsStore()
		const commentsStore = useCommentsStore()
		return { userStore, commentsStore, micropostsStore }
  	},

	components: {
		Footer,
        Menu
	},
	data() {
		return {
			microposts: [],
			comments: [],
			user: {
				id: '', 
				name: '', 
				email: '', 
				session_id: ''
			},
			show: '',
		}
	},
	mounted() {

	},
	methods: {
		microposts: this.setup()[2].actions.getMicropostsDB()
	},
	computed: {

	}
}
</script>

<style scoped>

</style>