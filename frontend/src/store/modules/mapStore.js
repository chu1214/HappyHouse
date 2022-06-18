import { http } from "@/js/http.js";

const mapStore = {
  namespaced: true,
  state: {
    houseInfos: [],
  },
  mutations: {
    async SET_HOUSE_INFOS(state, payload) {
      const response = await http.get("/map/searchinfo/range", {
        params: {
          lat1: payload.lat1,
          lng1: payload.lng1,
          lat2: payload.lat2,
          lng2: payload.lng2,
        },
      });
      state.houseInfos = [];
      state.houseInfos = response.data;
    },
  },
  actions: {},
};

export default mapStore;
