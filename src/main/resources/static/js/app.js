const CSRF_TOKEN = document.cookie.match(new RegExp(`XSRF-TOKEN=([^;]+)`))[1];
const instance = axios.create({
    headers: { "X-XSRF-TOKEN": CSRF_TOKEN }
});
const AXIOS = instance;

new Vue({
    el: '#main_container',
    data: {
        article: null,
        isShowingAddEditForm: false
    },
    methods: {
        getCurrentHostName(path) {
            return location.protocol
                .concat("//")
                .concat(window.location.hostname)
                .concat(":")
                .concat(window.location.port)
                .concat("/")
                .concat(path);
        },

        showAddArticleForm: function() {
            this.isShowingAddEditForm ^= true
        },

        addArticleButtonClick: function() {
            let newArticle = {
                "author": "",
                "title": $("#formArticleTitle").val(),
                "article": $("#formArticleBody").val()
            };
            axios.post(this.getCurrentHostName("api/article/"), newArticle)
                .then((response) => {
                    document.location.reload(true);
                })
                .catch((error) => {
                    console.log(error);
                });
        },

        editArticleButtonClick: function() {

        }
    }
});
