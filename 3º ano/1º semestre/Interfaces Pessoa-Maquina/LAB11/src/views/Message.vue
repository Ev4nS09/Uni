<template>
    <body class = "logout d-flex bg-black align-items-center vh-100">
        <div class = "d-gird container-xl rounded-2 border border-2 text-center w-25">
			<h1 class="text-white">{{message}}</h1>
        </div>
    </body>
</template>

<script>


import { useUserStore } from '@/store/user'


export default {

	setup() {
		const userStore = useUserStore()
		return { userStore }
  	},	

    data() {
        return {
            message: ''
        }
	},
	mounted() {
		this.setMessage()
	},


	methods: {
        setMessage() {
				if (this.$route.params.id == 1) {
                	this.message = "Success: Post updated"
                } 
				if (this.$route.params.id == 2) {
                	this.message = "Success: User deleted"
                }
				if (this.$route.params.id == 3) {
                	this.message = "Couldn't complete your order"
                }
				if (this.$route.params.id == 4) {
                	this.message = "Welcome! You can now login"
                }
				if (this.$route.params.id == 5) {
                	this.message = "Welcome back " + this.userStore.getUser.name + "!"
                }
				if (this.$route.params.id == 6) {
                	this.message = "Bye, come back soon!"
                }	
				if (this.$route.params.id == 7) {
                	this.message = "Your order was made"
                }																
				
				var timeleft = 2;
				var downloadTimer = setInterval(() => {
					if(timeleft <= 0){
						clearInterval(downloadTimer)
						this.$router.push('/menu')
					}
				timeleft -= 1
				}, 1000)
		},


	},
   

}
</script>