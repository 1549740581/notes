const app = new Vue({
    el: '#app',
    data: {
        books: [
            {
                id: 1,
                name: "算法导论",
                date: "2006-01",
                price: 85.0,
                count: 1
            },
            {
                id: 2,
                name: "算法",
                date: "2008-01",
                price: 75.0,
                count: 1
            },
            {
                id: 3,
                name: "Unix编程艺术",
                date: "2010-03",
                price: 105.3,
                count: 2
            },
            {
                id: 4,
                name: "Java编程思想",
                date: "2003-01",
                price: 100.5,
                count: 1
            }
        ]
    },
    filters: {
        finalPrice(price) {
            return "￥" + parseFloat(price).toFixed(2);
        }
    },
    methods: {
        decrement(idx) {
            this.books[idx].count--;
        },
        increment(idx) {
            this.books[idx].count++;
        },
        removeHandle(idx) {
            this.books.splice(idx, 1);
        }
    },
    computed: {
        totalPrice() {
            // let res = 0.0;
            // for (let i = 0; i < this.books.length; ++i) {
            //     res += this.books[i].count * this.books[i].price;
            // }
            // return res;

            // 注意这种方式i let idx in ...，idx是索引
            // let res = 0.0;
            // for (let idx in this.books) {
            //     res += this.books[idx].count * this.books[idx].price;
            // }
            // return res;

            // let res = 0.0;
            // for (let item of this.books) {
            //     res += item.count * item.price;
            // }
            // return res;

            // return this.books.reduce(function (pre, book) {
            //     return pre + book.price * book.count;
            // }, 0);

            return this.books.reduce((pre, book) => { return pre + book.price * book.count }, 0);
        }
    }
});