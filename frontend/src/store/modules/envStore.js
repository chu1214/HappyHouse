import { api } from "@/js/http.js";

const REST_API_KEY = process.env.VUE_APP_REST_API_KEY;

const envStore = {
  namespaced: true,
  state: {
    subway: [],
    oil: [],
    kids: [],
    school: [],
    edu: [],
    food: [],
    convin: [],
    cafe: [],
    hospital: [],
    medi: [],
    mart: [],
    bank: [],
    isChange: false,
  },
  getters: {
    checkIsChange(state) {
      return state.isChange;
    },
  },

  mutations: {
    SET_SUBWAY(state, payload) {
      state.subway = payload.documents;
    },
    SET_OIL(state, payload) {
      state.oil = payload.documents;
    },
    SET_KIDS(state, payload) {
      state.kids = payload.documents;
    },
    SET_SCHOOL(state, payload) {
      state.school = payload.documents;
    },
    SET_EDU(state, payload) {
      state.edu = payload.documents;
    },
    SET_FOOD(state, payload) {
      state.food = payload.documents;
    },
    SET_CONVIN(state, payload) {
      state.convin = payload.documents;
    },
    SET_CAFE(state, payload) {
      state.cafe = payload.documents;
    },
    SET_HOSPITAL(state, payload) {
      state.house = payload.documents;
    },
    SET_MEDI(state, payload) {
      state.medi = payload.documents;
    },
    SET_MART(state, payload) {
      state.mart = payload.documents;
    },
    SET_BANK(state, payload) {
      state.bank = payload.documents;
    },
    SET_IS_CHANGE(state, payload) {
      state.isChange = payload;
    },
  },
  actions: {
    async set_subway(store, payload) {
      try {
        const response = await api.get("", {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            category_group_code: "SW8",
            x: payload.x,
            y: payload.y,
            radius: 1300,
            sort: "distance",
          },
        });
        store.commit("SET_SUBWAY", response.data);
      } catch (error) {
        alert("조회 실패");
      }
    },
    async set_oil(store, payload) {
      try {
        const response = await api.get("", {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            category_group_code: "OL7",
            x: payload.x,
            y: payload.y,
            radius: 2000,
            sort: "distance",
          },
        });
        store.commit("SET_OIL", response.data);
      } catch (error) {
        alert("조회 실패");
      }
    },
    async set_kids(store, payload) {
      try {
        const response = await api.get("", {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            category_group_code: "PS3",
            x: payload.x,
            y: payload.y,
            radius: 3000,
            sort: "distance",
          },
        });
        store.commit("SET_KIDS", response.data);
      } catch (error) {
        alert("조회 실패");
      }
    },
    async set_school(store, payload) {
      try {
        const response = await api.get("", {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            category_group_code: "SC4",
            x: payload.x,
            y: payload.y,
            radius: 3000,
            sort: "distance",
          },
        });
        store.commit("SET_SCHOOL", response.data);
      } catch (error) {
        alert("조회 실패");
      }
    },
    async set_edu(store, payload) {
      try {
        const response = await api.get("", {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            category_group_code: "AC5",
            x: payload.x,
            y: payload.y,
            radius: 3000,
            sort: "distance",
          },
        });
        store.commit("SET_EDU", response.data);
      } catch (error) {
        alert("조회 실패");
      }
    },
    async set_food(store, payload) {
      try {
        const response = await api.get("", {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            category_group_code: "FD6",
            x: payload.x,
            y: payload.y,
            radius: 1000,
            sort: "distance",
          },
        });
        store.commit("SET_FOOD", response.data);
      } catch (error) {
        alert("조회 실패");
      }
    },
    async set_convin(store, payload) {
      try {
        const response = await api.get("", {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            category_group_code: "CS2",
            x: payload.x,
            y: payload.y,
            radius: 1000,
            sort: "distance",
          },
        });
        store.commit("SET_CONVIN", response.data);
      } catch (error) {
        alert("조회 실패");
      }
    },
    async set_cafe(store, payload) {
      try {
        const response = await api.get("", {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            category_group_code: "CE7",
            x: payload.x,
            y: payload.y,
            radius: 1000,
            sort: "distance",
          },
        });
        store.commit("SET_CAFE", response.data);
      } catch (error) {
        alert("조회 실패");
      }
    },
    async set_hospital(store, payload) {
      try {
        const response = await api.get("", {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            category_group_code: "HP8",
            x: payload.x,
            y: payload.y,
            radius: 2000,
            sort: "distance",
          },
        });
        store.commit("SET_HOSPITAL", response.data);
      } catch (error) {
        alert("조회 실패");
      }
    },
    async set_medi(store, payload) {
      try {
        const response = await api.get("", {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            category_group_code: "PM9",
            x: payload.x,
            y: payload.y,
            radius: 2000,
            sort: "distance",
          },
        });
        store.commit("SET_MEDI", response.data);
      } catch (error) {
        alert("조회 실패");
      }
    },
    async set_mart(store, payload) {
      try {
        const response = await api.get("", {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            category_group_code: "MT1",
            x: payload.x,
            y: payload.y,
            radius: 5000,
            sort: "distance",
          },
        });
        store.commit("SET_MART", response.data);
      } catch (error) {
        alert("조회 실패");
      }
    },
    async set_bank(store, payload) {
      try {
        const response = await api.get("", {
          headers: { Authorization: `KakaoAK ${REST_API_KEY}` },
          params: {
            category_group_code: "BK9",
            x: payload.x,
            y: payload.y,
            radius: 1000,
            sort: "distance",
          },
        });
        store.commit("SET_BANK", response.data);
      } catch (error) {
        alert("조회 실패");
      }
    },
  },
};

export default envStore;
