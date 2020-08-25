<template>
<div class="signin">
    <form>
        <h2 class="text-center">Login</h2>
        <div class="form-group has-error">
            <input type="text" class="form-control" name="username" placeholder="Username" v-model.trim="username" required="required" />
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password" placeholder="Password" v-model.trim="password" required="required" />
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-primary btn-lg btn-block" v-on:click="login_fun">
                Sign in
            </button>
        </div>
        <p><a href="#">Forgot Password?</a></p>
    </form>
</div>
</template>

<script>
import router from '../router'
import axios from 'axios'

export default {
  name: 'Signin',
  data () {
    return {
      username: '',
      password: ''
    }
  },
  beforeCreate: function () {
    document.body.classList.add('signin')
  },
  methods: {
    empty: function () {
      this.username = ''
      this.password = ''
    },
    login_fun: async function () {
      if (this.username !== '' && this.password !== '') {
        await axios.get('http://localhost:8080/Project/REST-API/login?username=' + this.username + '&password=' + this.password)
          .then(response => {
            // eslint-disable-next-line eqeqeq
            if (response.data != 'Incorrect username or password') {
              this.empty()
              // alert('Signin Successfull')
              router.push(response.data)
            } else {
              alert(response.data)
            }
          })
          .catch(error => {
            console.log(error)
          })
      } else {
        alert('A username and password must be present')
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style src="../../static/css/signin.css" scoped></style>
