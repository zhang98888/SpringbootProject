<template>
    <div class="IndexName">
        <div class="container">
            <div class="header">
                <h3>{{title}} <small>{{subTitle}}</small></h3>
                <slot name="right"></slot>
            </div>
            <slot></slot>
        </div>
    </div>
</template>

<script>
export default {
    name: "IndexName",
    props:{
        title: {
            type: String,
        },
        subTitle:{
            type: String,

        }
    }
}
</script>

<style lang="less">
.IndexName{
    .header{
        padding:35px 0;
        display: flex;
        align-items: flex-end;
        h3{
            flex: 1;
            font-size: 20px;
            font-weight: normal;
            margin-left: 0px;
            height: 30px;
            small{
                font-size: 10px;
                margin-left: 20px;
            }
        }
    }
}
</style>