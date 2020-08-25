<template>
<div class="adddoctor">
    <div class="container-fluid">
        <!-- Main container start -->
        <div class="main-container">
            <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
                <a class="navbar-brand" href="/"><img src="../../static/img/logo.png"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" :href="'/admin/' + admin_id + '/home'">My Home</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>

            <!-- Content wrapper start -->
        <div class="content-wrapper">
            <!-- Row start -->
            <div class="row gutters">
                <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="doctor-profile">
                                <div class="doctor-thumb">
                                    <img id="output" :src="url" alt="Doctor">
                                </div>
                                <div class="input-group mb-3">
                                    <div class="custom-file">
                                        <input type="file" ref="file" name="file1" class="custom-file-input" id="changeProfile" aria-describedby="changeProfile"  accept="image/*" @change="onFileSelected()" required/>
                                        <label class="custom-file-label" for="changeProfile">Update Image</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12">
                    <div class="card">
                        <div class="card-header">
                            <div class="card-title">Enter Doctor Details</div>
                        </div>
                        <div class="card-body">
                            <div class="row gutters">
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="fullName">Full Name</label>
                                        <input type="text" v-model="doctor.name" class="form-control" id="fullName" placeholder="Full Name">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="inputAge">Age</label>
                                        <input type="text" v-model="doctor.age" class="form-control" id="inputAge" placeholder="Age">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="inputDepartment">Department</label>
                                        <input type="text" v-model="doctor.department" class="form-control" id="inputDepartment" placeholder="Department">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="inputSpecialization">Specialization</label>
                                        <input type="text" v-model="doctor.specialization" class="form-control" id="inputSpeciality" placeholder="Specialization">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="inputAddress">Address</label>
                                        <input type="text" v-model="doctor.address" class="form-control" id="inputAddress" placeholder="Current Address">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="inputEmail">Email</label>
                                        <input type="email" v-model="doctor.email" class="form-control" id="inputEmail" placeholder="Email">
                                    </div>
                                </div>
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <div class="text-right">
                                        <button class="btn btn-primary" v-on:click="createAccount()">Create Profile</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12">
                    <div class="card">
                        <div class="card-header">
                            <div class="card-title">Create Account</div>
                        </div>
                        <div class="card-body">
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <div class="form-group">
                                        <label for="userName">User Name</label>
                                        <input type="text" v-model="doctor.username" class="form-control" id="userName" placeholder="User Name">
                                    </div>
                                </div>
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input type="password" v-model="doctor.password" class="form-control" id="password" placeholder="Password">
                                    </div>
                                </div>
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <div class="form-group">
                                        <label for="rePassword">Re-enter Password</label>
                                        <input type="password" v-model="doctor.repassword" class="form-control" id="rePassword" placeholder="New Password">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import router from '../router'
import axios from 'axios'

export default {
  name: 'AddDoctor',
  data () {
    return {
      admin_id: '',

      url: require('../../static/img/profile.png'),
      file: '',

      doctor: {
        name: '',
        age: '',
        department: '',
        specialization: '',
        address: '',
        email: '',
        username: '',
        password: '',
        repassword: ''
      }
    }
  },
  beforeCreate: function () {
    document.body.classList.add('adddoctor')
  },
  created () {
    this.admin_id = this.$route.params.id
    this.isActive()
  },
  methods: {
    empty: function () {
      this.doctor.name = ''
      this.doctor.age = ''
      this.doctor.department = ''
      this.doctor.specialization = ''
      this.doctor.address = ''
      this.doctor.email = ''
      this.doctor.username = ''
      this.doctor.password = ''
      this.doctor.repassword = ''
    },
    isActive: async function () {
      const currentObject = this
      await axios.get('http://localhost:8080/Project/REST-API/admin/' + this.admin_id)
        .then(response => {
          if (response.data.active === false) {
            router.push('/')
          }
        })
        .catch(function (error) {
          currentObject.serverError = true
          if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.log(error.response.data)
            console.log(error.response.status)
            console.log(error.response.headers)
            currentObject.serverErrorMessage = error.response.data.message
          } else if (error.request) {
            // The request was made but no response was received
            console.log(error.request)
          } else {
            // Something happened in setting up the request that triggered an Error
            console.log('Error', error.message)
          }
          console.log(error.config)
        })
    },
    onFileSelected: function (event) {
      const file = this.$refs.file.files[0]
      this.file = file
      //   this.file_size = file.size
      // var output = document.getElementById('output')
      // output.src = URL.createObjectURL(this.file)
      // output.onload = function () {
      //   URL.revokeObjectURL(output.src) // free memory
      // }
      this.url = URL.createObjectURL(this.file)
    },
    uploadImage: async function (doctorid) {
      const currentObject = this
      if (this.file !== '') {
        const formData = new FormData()
        formData.append('imagefile', this.file, this.file.name)
        try {
          await axios.post('http://localhost:8080/Project/REST-API/doctor/image/upload/' + doctorid, formData)
            .then(function (response) {
              if (response.data === 'Image uploaded successfully') {
                currentObject.url = require('../../static/img/profile.png')
                currentObject.file = ''
                // alert('Account created')
              } else {
                // alert('Image upload failed')
              }
            })
          // this.file = ''
          this.error = false
        } catch (err) {
          console.log(err)
          // this.message = 'Something went wrong';
          this.error = true
        }
      }
    },
    createAccount: async function () {
      if (this.doctor.password !== this.doctor.repassword) {
        alert('Password does not match')
      } else if (this.doctor.name && this.doctor.age && this.doctor.department && this.doctor.specialization && this.doctor.address && this.doctor.email && this.doctor.username && this.doctor.password && this.doctor.repassword && (this.doctor.password === this.doctor.repassword)) {
        await axios.post('http://localhost:8080/Project/REST-API/adddoctor/', {
          name: this.doctor.name,
          username: this.doctor.username,
          password: this.doctor.password,
          department: this.doctor.department,
          specialization: this.doctor.specialization,
          age: this.doctor.age,
          email: this.doctor.email,
          address: this.doctor.address
        })
          .then(response => {
            // console.log(response.data)
            // eslint-disable-next-line eqeqeq
            if (response.data == 'Email already exist' || response.data == 'Username already exist') {
              alert(response.data)
            } else {
              // console.log(response.data)
              this.uploadImage(response.data)
              this.empty()
              alert('Account created')
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

<style src="../../static/css/adddoctor.css" scoped>
</style>
