<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col align="center">
        <h1 class="underline mt-5">공지사항</h1>
      </b-col>
    </b-row>
    <notice-content />
  </b-container>
</template>

<script>
import NoticeContent from "@/components/notice/noticecontent/article/NoticeContent.vue";

export default {
  name: "NoticeDetail",
  components: {
    NoticeContent,
  },
};
</script>

<style scoped>
.underline {
  text-align: center;
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(84, 86, 92, 0.678) 30%
  );
}
</style>
