new Vue({
    el: '#main_container',
    data: {
        article: null,
        isShowingAddEditForm: false
    },
    methods: {
        showAddArticleForm: function() {
            this.isShowingAddEditForm ^= true
        },
        editArticleInForm: function () {
            $.ajax()
        }
    }
});
