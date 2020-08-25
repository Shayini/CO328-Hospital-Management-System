<template>
<div class="adminhome">
    <div id="home" v-if="!serverError">
        <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
            <a class="navbar-brand" href="/">
                <img src="../../static/img/logo.png" />
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link cursor_type" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link cursor_type" :href="'/admin/' + admin_id + '/addDoctor'">Add Doctor</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link cursor_type" :href="'/admin/' + admin_id + '/doctorDetail'">Doctors</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link cursor_type" onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Change Password</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link cursor_type" v-on:click="signout()">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <div class="modal1" id="id01" v-if="!serverError">
        <form class="modal1-content animate">
            <div class="imgcontainer">
                <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
            </div>
            <div class="containers">
                <changePassword></changePassword>
            </div>
        </form>
    </div>
    <div class="profile" v-if="!serverError">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="side-bar">
                        <h5 class="tittle">Professional Details</h5>
                        <img class="img-responsive"  id="output" :src="url" alt="Admin" />
                        <div class="card-body">
                            <div class="doctor-profile">
                                <div class="input-group mb-3">
                                    <div class="custom-file">
                                        <input type="file" ref="file" name="file1" class="custom-file-input" id="changeProfile" aria-describedby="changeProfile"  accept="image/*" @change="onFileSelected()" required/>
                                        <label class="custom-file-label" for="changeProfile">Update Image</label>
                                    </div>
                                </div>
                                    <button v-if="fileedited" class="btn btn-primary" v-on:click="uploadImage()">Upload</button>
                            </div>
                        </div>
                        <ul class="personal-info">
                            <li v-for="(value, name, index) in admin" :key="index">
                                <p>
                                    <span>{{datas[index]}}</span> {{value}}
                                </p>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="tab-contant">
                        <div class="tab-pane fade in active">
                            <div class="inside-sec">
                                <h5 class="tittle">About Me</h5>
                                <section class="about-me padding-top-10">
                                    <ul class="personal-info">
                                        <table>
                                            <tr>
                                                <li>
                                                    <p>
                                                        <span for="admin-name">Name</span>
                                                        <label class="" v-show="!showField('name')" @click="focusField('name')">{{admin.name}}</label>
                                                        <input v-model="admin.name" v-show="showField('name')" id="admin-name" type="text" class="" @focus="focusField('name')" @blur="blurField">
                                                    </p>
                                                </li>
                                                <li>
                                                    <p>
                                                        <span for="admin-age">Age</span>
                                                        <label class="" v-show="!showField('age')" @click="focusField('age')">{{admin.age}}</label>
                                                        <input v-model="admin.age" v-show="showField('age')" id="admin-age" type="text" class="" @focus="focusField('age')" @blur="blurField">
                                                    </p>
                                                </li>
                                            </tr>
                                            <tr>
                                                <li>
                                                    <p>
                                                        <span for="admin-location">Location</span>
                                                        <label class="" v-show="!showField('location')" @click="focusField('location')">{{admin.location}}</label>
                                                        <input v-model="admin.location" v-show="showField('location')" id="admin-location" type="text" class="" @focus="focusField('location')" @blur="blurField">
                                                    </p>
                                                </li>
                                                <li>
                                                    <p>
                                                        <span for="admin-phone">Phone</span>
                                                        <label class="" v-show="!showField('phone')" @click="focusField('phone')">{{admin.phone}}</label>
                                                        <input v-model="admin.phone" v-show="showField('phone')" id="admin-phone" type="text" class="" @focus="focusField('phone')" @blur="blurField">
                                                    </p>
                                                </li>
                                            </tr>
                                            <tr>
                                                <li>
                                                    <p>
                                                        <span for="admin-email">E-mail</span>
                                                        <label class="" v-show="!showField('email')" @click="focusField('email')">{{admin.email}}</label>
                                                        <input v-model="admin.email" v-show="showField('email')" id="admin-email" type="text" class="" @focus="focusField('email')" @blur="blurField">
                                                    </p>
                                                </li>
                                            </tr>
                                        </table>
                                    </ul>
                                </section>
                            </div>
                        </div>
                    </div>
                    <div class="contact-form">
                        <button type="button" class="disabled" value="submit" v-bind:disabled="!edited" id="btn_submit_1" v-on:click="update()" style="float: right;">Save</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="alert alert-warning" v-if="serverError">
        <strong>Warning!</strong> {{serverErrorMessage}} <!-- Server returned error, most likely the session has expired, please refresh the page and try again. -->
    </div>
</div>
</template>

<script>
import router from '../router'
import axios from 'axios'
import ChangePassword from '@/components/ChangePassword'

export default {
  name: 'AdminHome',
  components: {
    ChangePassword
  },
  data () {
    return {
      role: 'admin',
      admin_id: '',

      url: require('../../static/img/profile.png'),
      file: '',
      fileedited: false,

      datas: ['name', 'age', 'location', 'phone', 'e-mail'],
      admin: {
        name: 'Not filled',
        age: 'Not filled',
        location: 'Not filled',
        phone: 'Not filled',
        email: 'Not filled'
      },
      editField: '',
      edited: false,
      serverError: false,
      serverErrorMessage: ''
    }
  },
  beforeCreate: function () {
    document.body.classList.add('adminhome')
  },
  created () {
    this.admin_id = this.$route.params.id
    console.log(this.admin_id)
    this.get_data()
    this.getImage()
  },
  methods: {
    focusField (name) {
      this.edited = true
      document.getElementById('btn_submit_1').classList.remove('disabled')
      this.editField = name
    },
    blurField () {
      this.editField = ''
    },
    showField (name) {
      // eslint-disable-next-line eqeqeq
      return (this.admin[name] == '' || this.editField == name)
    },
    getImage: async function () {
      const currentObject = this
      await axios.get('http://localhost:8080/Project/REST-API/admin/image/get/' + this.admin_id)
        .then(response => {
          if (response.data !== '') {
            this.url = 'data:image/*;base64,' + response.data.pic_byte
          } else {
            this.url = require('../../static/img/profile.png')
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
      // this.file_name = file.name
      // this.file_size = file.size
      // var output = document.getElementById('output')
      // output.src = URL.createObjectURL(this.file)
      // output.onload = function () {
      //   URL.revokeObjectURL(output.src) // free memory
      // }
      this.url = URL.createObjectURL(this.file)
      this.fileedited = true
    },
    uploadImage: async function () {
      const currentObject = this
      if (this.file !== '') {
        const formData = new FormData()
        formData.append('imagefile', this.file, this.file.name)
        try {
          await axios.post('http://localhost:8080/Project/REST-API/admin/image/upload/' + this.admin_id, formData)
            .then(function (response) {
              if (response.data === 'Image updated successfully' || response.data === 'Image uploaded successfully') {
                alert(response.data)
                // console.log(response.data)
                currentObject.fileedited = false
              } else {
                alert(response.data)
                // console.log(response.data)
                currentObject.fileedited = true
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
    signout: async function () {
      await axios.put('http://localhost:8080/Project/REST-API/logout/admin/' + this.admin_id)
        .then(response => {
          if (response.data === 'Logout Successful') {
            // alert(response.data)
            router.push('/')
          } else {
            alert(response.data)
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
    get_data: async function () {
      const currentObject = this
      await axios.get('http://localhost:8080/Project/REST-API/admin/' + this.admin_id)
        .then(response => {
          if (response.data.active === true) {
            if (response.data.name != null) this.admin.name = response.data.name
            if (response.data.age != null) this.admin.age = response.data.age
            if (response.data.address != null) this.admin.location = response.data.address
            if (response.data.contact_no != null) this.admin.phone = response.data.contact_no
            if (response.data.email != null) this.admin.email = response.data.email
          } else {
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
    update: async function () {
      if (this.admin.name && this.admin.age && this.admin.location && this.admin.email) {
        await axios.put('http://localhost:8080/Project/REST-API/updateadmin/' + this.admin_id, {
          name: this.admin.name !== 'Not filled' ? this.admin.name : '',
          age: this.admin.age !== 'Not filled' ? this.admin.age : '',
          address: this.admin.location !== 'Not filled' ? this.admin.location : '',
          contact_no: this.admin.phone !== 'Not filled' ? this.admin.phone : '',
          email: this.admin.email !== 'Not filled' ? this.admin.email : ''
        })
          .then(response => {
          // eslint-disable-next-line eqeqeq
            if (response.data != 'Update successful') {
              alert(response.data)
              this.get_data()
            } else {
              alert(response.data)
              window.location.reload()
            }
          })
          .catch(function (error) {
            if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
              console.log(error.response.data)
              console.log(error.response.status)
              console.log(error.response.headers)
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

<style src="../../static/css/adminhome.css" scoped>
</style>
