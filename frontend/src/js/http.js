import axios from "axios";

// axios 객체 생성
const http = axios.create({
  baseURL: "http://localhost:80/",
  headers: {
    "Content-type": "application/json",
  },
});

const api = axios.create({
  baseURL: "https://dapi.kakao.com/v2/local/search/category.json",
  headers: {
    "Content-type": "application/json",
  },
});

export { http, api };
