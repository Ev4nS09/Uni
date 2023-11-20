<template>
<div>
		
	<Menu />
    <form action = "register_action.php" method = "post" class = "d-gird container-sm rounded-2 border border-2 justify-content-center align-text-center w-auto">
        <div class = "mb-4 d-flex justify-content-center align-text-center">
            <h1 class="text-white">{$message}</h1>
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter Username:</label>
            <input type="text" value="{$username}" name="username" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter email:</label>
            <input type="email" value="{$email}" name="email" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter password:</label>
            <input type="password" name="password" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Confirm password:</label>
            <input type="password" name="confirm_password" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-4 d-flex justify-content-center align-text-center">
            <button type="submit" class = "btn btn-outline-light d-inline mt-4 mb-1">Register</button>
        </div>
        <div class=" d-flex justify-content-center align-text-center">
            <h6 class="text-white">Already have an account? <br> 
                <div class="d-flex justify-content-center align-text-center">
                    <a href="login.php">Login here</a>
                </div>
            </h6>
        </div>
        <div class="mt-3 d-flex justify-content-center align-text-center">
            <h6 class="text-white">Go home <a href="index.php">here</a>
            </h6>
        </div>
    </form>
	
</div>
</template>

<script>

import Menu from '@/components/Menu.vue'

import { useUserStore } from '@/store/user'

export default {

	setup() {
		const userStore = useUserStore()
		return { userStore }
  	},	
	components: {
        Menu
	},
	data() {
      return {	
        user: {
			name: '',
			email: '',
			password: '',
        },
		passwordConfirmation: '',
		submitting: true,
		error: true,	
      }
    },
	
	methods: {
	
	},
	
	computed: {

	},
	directives: {

	},
	mounted() {


	}
}
</script>

<style scoped>


</style>