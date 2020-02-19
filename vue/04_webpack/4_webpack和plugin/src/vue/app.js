export default {
    template: `
        <div>
            <h2>{{message}}</h2>
            <button @click="onClick">点击</button>
        </div>
    `,
    data() {
        return {
            message: 'msg'
        }
    },
    methods: {
        onClick() {
            console.log("click");
        }
    },
}
