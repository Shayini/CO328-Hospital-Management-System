<template>
<div class="changepassword">
    <!-- <div class="signup-form"> -->
    <form>
        <h2 class="text-center">Change Password</h2>
        <!-- <p>Please fill in this form to create an account!</p> -->
        <hr />
        <div class="form-group">
            <password v-model.trim="current_password" :toggle="true" :badge="false" :showStrengthMeter="false" placeholder="Old Password" required="required" />
        </div>
        <div class="form-group">
            <password v-model.trim="new_password1" :toggle="true" :secureLength="7" placeholder="New Password" @score="showScore" @feedback="showFeedback" required="required" />
        </div>
        <div class="form-group">
            <password v-model.trim="new_password2" :toggle="true" :badge="false" :showStrengthMeter="false" placeholder="New Password" required="required" />
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-primary btn-lg disabled" id="changepassword_button" v-bind:disabled="!isfilled" v-on:click="changepassword()">Change Password</button>
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
  name: 'ChangePassword',
  components: {
    Password
  },
  data () {
    return {
      id: '',
      role: '',
      current_password: '',
      new_password1: '',
      new_password2: '',
      password_score: 0
    }
  },
  beforeCreate: function () {
    document.body.classList.add('changepassword')
  },
  created () {
    console.log(this.$parent.role)
    this.id = this.$route.params.id
    console.log(this.id)
  },
  computed: {
    isfilled: function () {
      return this.current_password && this.new_password1 && this.new_password2 && (this.password_score >= 3)
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
      this.current_password = ''
      this.new_password1 = ''
      this.new_password2 = ''
      this.password_score = 0
    },
    changepassword: async function () {
      if (this.new_password1 !== this.new_password2) {
        alert('New Password does not match')
      } else if (this.new_password1 && this.new_password2 && this.current_password && (this.new_password1 === this.new_password2)) {
        await axios.put('http://localhost:8080/Project/REST-API/changepassword/' + this.$parent.role + '/' + this.id + '?current_password=' + this.current_password + '&new_password=' + this.new_password1)
          .then(response => {
            // console.log(response.data)
            // eslint-disable-next-line eqeqeq
            if (response.data == 'Password Updated Successfully') {
              // this.empty()
              alert(response.data)
              this.$parent.signout()
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
        alert('Please fill the required fields')
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style src="../../static/css/signup.css" scoped>
</style>
