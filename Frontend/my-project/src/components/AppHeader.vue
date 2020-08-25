<template>
<div>
  <button class="btn" id="aa" v-show="loggedIn">Logout</button>
</div>
</template>

<script>
export default {
  name: 'app-header',
  data () {
    return {
      loggedIn: false
    }
  }
}
</script>
