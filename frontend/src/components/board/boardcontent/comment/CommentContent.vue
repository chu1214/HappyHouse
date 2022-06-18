<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <h5 align="left">
        <b-icon icon="chat-left-text" scale="1" class="mt-5"> </b-icon>
        &nbsp;comments ({{ comments.length }})
      </h5>
      <b-table-simple
        hover
        class="mt-3"
        style="border-top: 1px solid gray; border-collapse: collapse"
      >
        <b-tbody>
          <comment-item
            align="center"
            v-for="comment in comments"
            :key="comment.commentno"
            v-bind="comment"
          />
        </b-tbody>
      </b-table-simple>
      <div style="border: 1px solid gray; border-radius: 5px" class="p-2">
        <b-form>
          <b-form-group id="userid-group" label-for="userid">
            <b-input-group size="m">
              <b-input-group-prepend is-text>
                <b-icon icon="person-fill"></b-icon>
              </b-input-group-prepend>
              <b-form-input
                id="userid"
                :disabled="false"
                v-model="comment.userid"
                type="text"
                required
                placeholder="작성자를 입력하세요"
                readonly
              ></b-form-input>
            </b-input-group>
          </b-form-group>
          <b-form-group id="content-group" label-for="content">
            <b-input-group size="m" class="mb-2">
              <b-input-group-prepend is-text>
                <b-icon icon="card-text"></b-icon>
              </b-input-group-prepend>
              <b-form-textarea
                id="content"
                v-model="comment.content"
                placeholder="댓글을 입력하세요"
                rows="4"
              ></b-form-textarea>
            </b-input-group>
          </b-form-group>
        </b-form>
        <div align="right">
          <b-button
            pill
            @click="onSubmit"
            variant="outline-secondary"
            class="m-1"
            ><b-icon
              icon="check"
              variant="dark"
              font-scale="2"
              style="width: 70px"
            ></b-icon
          ></b-button>
        </div>
      </div>
    </b-container>
  </div>
</template>

<script>
import { http } from "@/js/http.js";
import CommentItem from "@/components/board/boardcontent/comment/item/CommentItem.vue";
export default {
  name: "CommentContent",
  components: {
    CommentItem,
  },

  data() {
    return {
      comments: [],
      comment: {},
    };
  },

  async created() {
    const articleno = this.$route.params.articleno;
    console.log(articleno);
    try {
      const response = await http.get(`/comment/list/${articleno}`);
      console.log(response.data);
      this.comments = response.data;
    } catch (error) {
      alert("댓글 조회 실패");
    }
    const data = JSON.parse(sessionStorage.getItem("vuex"));
    const userid = data.memberStore.userInfo.userid;
    this.comment.userid = userid;
  },

  methods: {
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.comment.userid &&
        ((msg = "작성자를 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.comment.content &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else {
        console.log("hi");
        this.registComment();
      }
    },

    async registComment() {
      this.comment.articleno = this.$route.params.articleno;
      const response = await http.post(`/comment`, this.comment);
      let msg = "등록 처리시 문제가 발생했습니다.";
      console.log(response.data);
      if (response.data == 1) {
        msg = "등록이 완료되었습니다.";
      }
      alert(msg);
      this.$router.go();
    },
  },
};
</script>

<style scoped></style>
