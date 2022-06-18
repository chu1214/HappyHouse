<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col align="center">
        <h1 class="underline mt-5">글보기</h1>
      </b-col>
    </b-row>
    <article-content />
    <comment-content />
  </b-container>
</template>

<script>
import ArticleContent from "@/components/board/boardcontent/article/ArticleContent.vue";
import CommentContent from "@/components/board/boardcontent/comment/CommentContent.vue";

export default {
  name: "BoardDetail",
  components: {
    ArticleContent,
    CommentContent,
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
