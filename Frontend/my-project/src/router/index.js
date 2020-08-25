import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home'
import DoctorHome from '@/components/DoctorHome'
import DoctorBookingTable from '@/components/DoctorBookingTable'
import DoctorScheduleTable from '@/components/DoctorScheduleTable'
import DoctorDetail from '@/components/DoctorDetail'
import UserHome from '@/components/UserHome'
import UserBookingTable from '@/components/UserBookingTable'
import AdminHome from '@/components/AdminHome'
import AddDoctor from '@/components/AddDoctor'
import Signin from '@/components/Signin'
import Signup from '@/components/Signup'
import ChangePassword from '@/components/ChangePassword'
import Booking from '@/components/Booking'
import AddSchedule from '@/components/AddSchedule'
import AA from '@/components/aa'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/doctor/:id/home',
    name: 'DoctorHome',
    component: DoctorHome
  },
  {
    path: '/doctor/:id/addschedule',
    name: 'AddSchedule',
    component: AddSchedule
  },
  {
    path: '/doctor/:id/doctorbookingtable',
    name: 'DoctorBookingTable',
    component: DoctorBookingTable
  },
  {
    path: '/doctor/:id/doctorscheduletable',
    name: 'DoctorScheduleTable',
    component: DoctorScheduleTable
  },
  {
    path: '/user/:id/home',
    name: 'UserHome',
    component: UserHome
  },
  {
    path: '/user/:id/booking',
    name: 'Booking',
    component: Booking
  },
  {
    path: '/doctor/:id/userbookingtable',
    name: 'UserBookingTable',
    component: UserBookingTable
  },
  {
    path: '/admin/:id/home',
    name: 'AdminHome',
    component: AdminHome
  },
  {
    path: '/admin/:id/doctorDetail',
    name: 'DoctorDetail',
    component: DoctorDetail
  },
  {
    path: '/admin/:id/addDoctor',
    name: 'AddDoctor',
    component: AddDoctor
  },
  {
    path: '/signin',
    name: 'Signin',
    component: Signin
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/:role/:id/changepassword',
    name: 'ChangePassword',
    component: ChangePassword
  },
  {
    path: '/aa',
    name: 'AA',
    component: AA
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
