<template>
  <Header />
	<div class="container">
		<div class="row">
			<div v-for="order in orders" class="col-md">
				<div class="border rounded container p-3 m-3">
					<div class="text-center">
            <div v-for="item in order.items" class="col-md">
              <div class="border rounded container p-3 m-3">
						    <br>
						    <h5 class="text-white">{{ item.name }}</h5>
                <h2> pato</h2>
              </div>
            </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import Header from '@/components/Header.vue'

import { useOrdersStore } from '@/store/orders'
import { useUserStore } from '@/store/user'

export default {
	setup() {
		const ordersStore = useOrdersStore()
		const userStore = useUserStore()
		return { userStore, ordersStore }
  	},
    components: {
        Header
	},
	data() {
		return {
      isHidden: false,
      id: 0,
			orders: [],
      user: this.userStore.getUser,
      userLoggedIn: Object.keys(this.userStore.getUser).length !== 0,
		}
	},
	async mounted() {
		await this.ordersStore.getMyOrdersDB()
		this.orders = this.ordersStore.getOrders
    console.log(this.orders)
	},
	methods: {
	},
  computed: {

    },
}
</script>