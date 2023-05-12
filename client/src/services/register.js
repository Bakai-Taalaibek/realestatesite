import axios from 'axios'
const baseUrl = 'http://localhost:3001/api/v1/auth/register'

const register = async credentials => {
  const response = await axios.post(baseUrl, credentials)
  return response.data
}

export default register

