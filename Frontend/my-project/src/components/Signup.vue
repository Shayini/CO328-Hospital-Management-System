<template>
<div class="signup">
    <!-- <div class="signup-form"> -->
    <form>
        <h2 class="text-center">Sign Up</h2>
        <p>Please fill in this form to create an account!</p>
        <hr />
        <div class="form-group">
            <div class="row">
                <div class="col-6">
                    <input type="text" class="form-control" name="first_name" placeholder="First Name" v-model.trim='user.fname' required="required" />
                </div>
                <div class="col-6">
                    <input type="text" class="form-control" name="last_name" placeholder="Last Name" v-model.trim='user.lname' required="required" />
                </div>
            </div>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="username" placeholder="Username" v-model.trim='user.username' required="required" />
        </div>
        <div class="form-group">
            <input type="email" class="form-control" name="email" placeholder="Email" v-model.trim='user.email' required="required" />
        </div>
        <div class="form-group">
            <password v-model.trim="user.password1" :toggle="true" :secureLength="7" placeholder="Password" @score="showScore" @feedback="showFeedback" required="required" />
        </div>
        <div class="form-group">
            <password v-model.trim="user.password2" :toggle="true" :badge="false" :showStrengthMeter="false" placeholder="Confirm Password" required="required" />
        </div>
        <div class="form-group">
            <label class="checkbox-inline">
                <input type="checkbox" v-model="user.selected" /> I accept the
                <a href="#">Terms of Use</a> &amp;
                <a href="#">Privacy Policy</a>
            </label>
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-primary btn-lg" id="signup_button" v-bind:disabled="!isfilled" v-on:click="createAccount()">Sign Up</button>
        </div>
        <!-- <div class="hint-text">
            Already have an account?
            <a href="#">Login here</a>
        </div> -->
    </form>
    <!-- </div> -->
</div>
</template>

<script>
// import router from '../router'
import axios from 'axios'
import Password from 'vue-password-strength-meter'

export default {
  name: 'Signup',
  components: {
    Password
  },
  data () {
    return {
      user: {
        fname: '',
        lname: '',
        username: '',
        email: '',
        password1: '',
        password2: '',
        selected: false
      },
      password_score: 0
    }
  },
  beforeCreate: function () {
    document.body.classList.add('signup')
  },
  computed: {
    isfilled: function () {
      return this.user.selected && this.user.fname && this.user.lname && this.user.username && this.user.email && this.user.password1 && this.user.password2 && (this.password_score >= 3)
    }
  },
  methods: {
    showFeedback ({ suggestions, warning }) {
      console.log('🙏', suggestions)
      console.log('⚠', warning)
    },
    showScore (score) {
      console.log('💯', score)
      this.password_score = score
    },
    empty: function () {
      this.user.fname = ''
      this.user.lname = ''
      this.user.username = ''
      this.user.email = ''
      this.user.password1 = ''
      this.user.password2 = ''
      this.user.selected = false
    },
    createAccount: async function () {
      if (this.user.password1 !== this.user.password2) {
        alert('Signup Unsuccessfull\nPassword does not match')
      } else if (this.user.selected && this.user.fname && this.user.username && this.user.email && this.user.password1 && this.user.password2 && (this.user.password1 === this.user.password2)) {
        await axios.post('http://localhost:8080/Project/REST-API/adduser/', {
          name: this.user.fname + ' ' + this.user.lname,
          username: this.user.username,
          password: this.user.password1,
          email: this.user.email
        })
          .then(response => {
            // console.log(response.data)
            // eslint-disable-next-line eqeqeq
            if (response.data == 'User account created Successfully') {
              alert(response.data)
              this.empty()
              this.$root.$emit('Close signup tab', 'Close signup tab')
            } else {
              alert(response.data)
            }
          })
          .catch(function (error) {
            if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
              console.log(error.response.data)
              console.log(error.response.status)
              console.log(error.response.headers)
            //   currentObject.serverErrorMessage = error.response.data.message
            } else if (error.request) {
            // The request was made but no response was received
              console.log(error.request)
            } else {
            // Something happened in setting up the request that triggered an Error
              console.log('Error', error.message)
            }
            console.log(error.config)
          })
      } else {
        alert('Signup Unsuccessfull\nPlease fill the required fields')
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style src="../../static/css/signup.css" scoped>
</style>
