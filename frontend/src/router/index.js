import Vue from "vue";
import VueRouter from "vue-router";
import BoardView from "@/views/BoardView.vue";
import WelcomeView from "@/views/WelcomeView.vue";
import MemberView from "@/views/MemberView.vue";
import NoticeView from "@/views/NoticeView.vue";
import MapView from "@/views/MapView.vue";
import NewsView from "@/views/NewsView.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "memberLogin" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

const onlyNoAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    next();
    // router.push({ name: "signIn" });
  } else {
    next({ name: "map" });
    // console.log("로그인 했다.");
  }
};

const routes = [
  {
    path: "/",
    name: "welcome",
    beforeEnter: onlyNoAuthUser,
    redirect: "/login",
    component: WelcomeView,
    children: [
      {
        path: "login",
        name: "memberLogin",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "register",
        name: "memberRegister",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "findId",
        name: "memberFindId",
        component: () => import("@/components/user/MemberFindId.vue"),
      },
      {
        path: "findPass",
        name: "memberFindPass",
        component: () => import("@/components/user/MemberFindPass.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    beforeEnter: onlyAuthUser,
    redirect: "/board/list",
    component: BoardView,
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "regist",
        name: "boardRegister",
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "modify",
        name: "boardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "delete/:articleno",
        name: "boardDelete",
        component: () => import("@/components/board/BoardDelete.vue"),
      },
    ],
  },
  {
    path: "/member",
    name: "member",
    beforeEnter: onlyAuthUser,
    redirect: "/member/detail",
    component: MemberView,
    children: [
      {
        path: "detail",
        name: "memberDetail",
        component: () => import("@/components/user/MemberDetail.vue"),
      },
      {
        path: "modify",
        name: "memberModify",
        component: () => import("@/components/user/MemberModify.vue"),
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    beforeEnter: onlyAuthUser,
    redirect: "/notice/list",
    component: NoticeView,
    children: [
      {
        path: "list",
        name: "noticeList",
        component: () => import("@/components/notice/NoticeList.vue"),
      },
      {
        path: "regist",
        name: "noticeRegister",
        component: () => import("@/components/notice/NoticeRegister.vue"),
      },
      {
        path: "modify",
        name: "noticeModify",
        component: () => import("@/components/notice/NoticeModify.vue"),
      },
      {
        path: "detail/:articleno",
        name: "noticeDetail",
        component: () => import("@/components/notice/NoticeDetail.vue"),
      },
      {
        path: "delete/:articleno",
        name: "noticeDelete",
        component: () => import("@/components/notice/NoticeDelete.vue"),
      },
    ],
  },
  {
    path: "/map",
    name: "map",
    beforeEnter: onlyAuthUser,
    component: MapView,
  },
  {
    path: "/news",
    name: "news",
    beforeEnter: onlyAuthUser,
    redirect: "/news/list",
    component: NewsView,
    children: [
      {
        path: "list",
        name: "noticeList",
        component: () => import("@/components/news/NewsList.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  routes,
});

export default router;
