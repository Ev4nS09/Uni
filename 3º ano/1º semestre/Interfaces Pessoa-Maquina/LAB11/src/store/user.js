import { defineStore } from 'pinia'

export const useUserStore = defineStore({
    id: 'user',
    state: () => ({
        user: { 
        },
        tmp_user:{

		},
    }),
    getters: {
        getUser: (state) => {
            return state.user;
        },   
		getTmpUser: (state) => {
            return state.tmp_user;
        },   
    }, 
    actions: {
		loginUser(user){
            this.user = user
			this.tmp_user = user
        },
        logoutUser(){
            this.user = {}
        },
        tmpUser(user){
            this.tmp_user = user
        }, 	
        async userExistsDB(user) {
			try {
				const response = await fetch(`http://daw.deei.fct.ualg.pt/~a76943/LAB8_10/api/users.php?email=${user.email}`, {
					method: 'GET',
				})
                const data = await response.json()
                console.log(data)
				if ( data !== null) {
                    alert('Email already exists')
                    return true
                }
                else {
                    //remember temporary user
                    this.tmpUser(user)
                    return false
                }                   
			} 
			catch (error) {
				console.error(error)
				alert('Error: Database connection failed in phase 1')
				return false
			}
		}, 
        async addUserDB() {
			try {
                const response = await fetch('http://daw.deei.fct.ualg.pt/~a76943/LAB8_10/api/users.php', {
					method: 'POST',
					body: JSON.stringify(this.tmp_user),
					headers: { 'Content-type': 'text/html; charset=UTF-8' },
				})
				const data = await response.json()
				console.log('received data:', data)
                return true
			} 
			catch (error) {
				console.error(error)
				alert('Error: Database connection failed in phase 2')	
				return false			
			}
		},
        async loginUserDB(user) {
			try {
				const response = await fetch(`http://daw.deei.fct.ualg.pt/~a76943/LAB8_10/api/users.php?email=${user.email}&password=${user.password}`)
				const data = await response.json()
				if ( data == null) {
					alert('Error: Wrong credentials')
					return false
                }
                else {
					//add new user
					console.log('received data:', data)
					this.loginUser(data)
                    return true
                }

			} 
			catch (error) {
				console.error(error)
				alert('Error: Database connection failed')	
				return false			
			}
		}, 
        async logoutUserDB(session_id) {
			try {
				const response = await fetch(`http://daw.deei.fct.ualg.pt/~a76943/LAB8_10/api/users.php?session_id=${session_id}`)
				const data = await response.json()
                console.log('received data:',data)
                this.logoutUser()
			} 
			catch (error) {
				console.error(error)
			}
		},   
    }

})
