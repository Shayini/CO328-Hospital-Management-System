<template>
<div class="doctorhome">
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
                        <a class="nav-link cursor_type" onclick="document.getElementById('id02').style.display='none';document.getElementById('id01').style.display='block'" style="width:auto;">Add Schedules</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link cursor_type" onclick="document.getElementById('id01').style.display='none';document.getElementById('id02').style.display='block'" style="width:auto;">Change Password</a>
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
                <addSchedule></addSchedule>
            </div>
        </form>
    </div>
    <div class="modal1" id="id02" v-if="!serverError">
        <form class="modal1-content animate">
            <div class="imgcontainer">
                <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
            </div>
            <div class="containers2">
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
                        <img class="img-responsive"  id="output" :src="url" alt="Doctor" />
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
                            <li v-for="(value, name, index) in doctor" :key="index">
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
                                                        <span for="doctor-name">Name</span>
                                                        <label class="" v-show="!showField('name')" @click="focusField('name')">{{doctor.name}}</label>
                                                        <input v-model="doctor.name" v-show="showField('name')" id="doctor-name" type="text" class="" @focus="focusField('name')" @blur="blurField">
                                                    </p>
                                                </li>
                                                <li>
                                                    <p>
                                                        <span for="doctor-age">Age</span>
                                                        <label class="" v-show="!showField('age')" @click="focusField('age')">{{doctor.age}}</label>
                                                        <input v-model="doctor.age" v-show="showField('age')" id="doctor-age" type="text" class="" @focus="focusField('age')" @blur="blurField">
                                                    </p>
                                                </li>
                                            </tr>
                                            <tr>
                                                <li>
                                                    <p>
                                                        <span for="doctor-location">Location</span>
                                                        <label class="" v-show="!showField('location')" @click="focusField('location')">{{doctor.location}}</label>
                                                        <input v-model="doctor.location" v-show="showField('location')" id="doctor-location" type="text" class="" @focus="focusField('location')" @blur="blurField">
                                                    </p>
                                                </li>
                                                <li>
                                                    <p>
                                                        <span for="doctor-department">Department</span>
                                                        <label class="" v-show="!showField('department')" @click="focusField('department')">{{doctor.department}}</label>
                                                        <input v-model="doctor.department" v-show="showField('department')" id="doctor-department" type="text" class="" @focus="focusField('department')" @blur="blurField">
                                                    </p>
                                                </li>
                                            </tr>
                                            <tr>
                                                <li>
                                                    <p>
                                                        <span for="doctor-specialization">Specialization</span>
                                                        <label class="" v-show="!showField('specialization')" @click="focusField('specialization')">{{doctor.specialization}}</label>
                                                        <input v-model="doctor.specialization" v-show="showField('specialization')" id="doctor-specialization" type="text" class="" @focus="focusField('specialization')" @blur="blurField">
                                                    </p>
                                                </li>
                                                <li>
                                                    <p>
                                                        <span for="doctor-phone">Phone</span>
                                                        <label class="" v-show="!showField('phone')" @click="focusField('phone')">{{doctor.phone}}</label>
                                                        <input v-model="doctor.phone" v-show="showField('phone')" id="doctor-phone" type="text" class="" @focus="focusField('phone')" @blur="blurField">
                                                    </p>
                                                </li>
                                            </tr>
                                            <tr>
                                                <li>
                                                    <p>
                                                        <span for="doctor-email">E-mail</span>
                                                        <label class="" v-show="!showField('email')" @click="focusField('email')">{{doctor.email}}</label>
                                                        <input v-model="doctor.email" v-show="showField('email')" id="doctor-email" type="text" class="" @focus="focusField('email')" @blur="blurField">
                                                    </p>
                                                </li>
                                            </tr>
                                        </table>
                                    </ul>
                                    <!-- <h5 class="tittle">I’m Web Designer</h5>
                                    <div class="padding-20">
                                        <p>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed, lacinia in, mi. Cras vel lorem. Etiam pellentesque aliquet tellus. Phasellus pharetra nulla ac diam.
                                            <br />
                                            <br />Quisque semper justo at risus. Donec venenatis, turpis vel hendrerit interdum, dui ligula ultricies purus, sed posuere libero dui id orci. Nam congue, pede vitae dapibus aliquet, elit magna vulputate arcu, vel tempus metus leo non est. Etiam sit amet lectus quis est congue mollis. Phasellus congue lacus eget neque. Phasellus ornare, ante vitae consectetuer consequat, purus sapien ultricies dolor, et mollis pede metus eget nisi.
                                            <br />
                                            <br />
                                        </p>
                                    </div> -->
                                </section>
                            </div>
                        </div>
                    </div>
                    <div class="contact-form">
                        <button type="button" class="disabled" value="submit" v-bind:disabled="!edited" id="btn_submit_1" v-on:click="update()" style="float: right;">Save</button>
                    </div>
                    <div class="detail">
                      <details><summary>Booking Details</summary>
                          <doctorBookingTable style="padding-top: 10px;"></doctorBookingTable>
                      </details>
                      <details><summary>Schedule Details</summary>
                          <doctorScheduleTable style="padding-top: 10px;"></doctorScheduleTable>
                      </details>
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
import DoctorBookingTable from '@/components/DoctorBookingTable'
import DoctorScheduleTable from '@/components/DoctorScheduleTable'
import AddSchedule from '@/components/AddSchedule'
import ChangePassword from '@/components/ChangePassword'

export default {
  name: 'DoctorHome',
  components: {
    AddSchedule,
    DoctorBookingTable,
    DoctorScheduleTable,
    ChangePassword
  },
  data () {
    return {
      role: 'doctor',
      doctor_id: '',

      url: require('../../static/img/profile.png'),
      file: '',
      fileedited: false,

      datas: ['name', 'age', 'location', 'department', 'specialization', 'phone', 'e-mail'],
      doctor: {
        name: 'Not filled',
        age: 'Not filled',
        location: 'Not filled',
        department: 'Not filled',
        specialization: 'Not filled',
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
    document.body.classList.add('doctorhome')
  },
  created () {
    this.doctor_id = this.$route.params.id
    console.log(this.doctor_id)
    this.get_data()
    this.getImage()
  },
  mounted: function () {
    this.$root.$on('logout', (text) => { // here you need to use the arrow function
      // console.log(text)
      this.signout()
    })
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
      return (this.doctor[name] == '' || this.editField == name)
    },
    getImage: async function () {
      const currentObject = this
      await axios.get('http://localhost:8080/Project/REST-API/doctor/image/get/' + this.doctor_id)
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
          await axios.post('http://localhost:8080/Project/REST-API/doctor/image/upload/' + this.doctor_id, formData)
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
      await axios.put('http://localhost:8080/Project/REST-API/logout/doctor/' + this.doctor_id)
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
      await axios.get('http://localhost:8080/Project/REST-API/doctor/' + this.doctor_id)
        .then(response => {
          if (response.data.active === true) {
            if (response.data.name != null) this.doctor.name = response.data.name
            if (response.data.age != null) this.doctor.age = response.data.age
            if (response.data.address != null) this.doctor.location = response.data.address
            if (response.data.department != null) this.doctor.department = response.data.department
            if (response.data.specialization != null) this.doctor.specialization = response.data.specialization
            if (response.data.contact_no != null) this.doctor.phone = response.data.contact_no
            if (response.data.email != null) this.doctor.email = response.data.email
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
      if (this.doctor.name && this.doctor.age && this.doctor.location && this.doctor.department && this.doctor.specialization && this.doctor.email) {
        await axios.put('http://localhost:8080/Project/REST-API/updatedoctor/' + this.doctor_id, {
          name: this.doctor.name !== 'Not filled' ? this.doctor.name : '',
          age: this.doctor.age !== 'Not filled' ? this.doctor.age : '',
          address: this.doctor.location !== 'Not filled' ? this.doctor.location : '',
          department: this.doctor.department !== 'Not filled' ? this.doctor.department : '',
          specialization: this.doctor.specialization !== 'Not filled' ? this.doctor.specialization : '',
          contact_no: this.doctor.phone !== 'Not filled' ? this.doctor.phone : '',
          email: this.doctor.email !== 'Not filled' ? this.doctor.email : ''
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

<style src="../../static/css/doctorhome.css" scoped>
</style>
