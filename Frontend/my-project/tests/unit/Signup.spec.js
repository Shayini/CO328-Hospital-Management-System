/* eslint-disable no-undef */
import { mount } from '@vue/test-utils'
import Signup from '@/components/Signup'

describe('Signup', () => {
  test('if any required field is not filled, signup button should be disabled', () => {
    const wrapper = mount(Signup, {
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
      }
    })
    expect(wrapper.find('#signup_button').element.hasAttribute('disabled')).toBe(true)
  })

  test('if all required field are filled,signup button should be enabled', () => {
    const wrapper = mount(Signup, {
      data () {
        return {
          user: {
            fname: 'Kapil',
            lname: 'Rajh',
            username: 'kapil',
            email: 'kapil@gmail.com',
            password1: 'engpdn2020@',
            password2: 'engpdn2020@',
            selected: true
          },
          password_score: 4
        }
      }
    })
    expect(wrapper.find('#signup_button').element.hasAttribute('disabled')).toBe(false)
  })
})
