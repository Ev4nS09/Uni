<template>
<div></div>
</template>

<script>

import { useBasketStore } from '@/store/basket'
import { useUserStore } from '@/store/user'

export default {
	setup() {
		const basketStore = useBasketStore()
		const userStore = 	useUserStore()	
		return { basketStore, userStore }
  	},    
    methods: {	

    },
    computed: {
        
    },
}

</script>  
