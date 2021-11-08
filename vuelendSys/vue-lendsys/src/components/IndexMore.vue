<template>
    <RouterLink :to="path" class="IndexMore">
        <span>more
            <i class="el-icon-arrow-right"></i>
        </span>
    </RouterLink>
    
</template>

<script>
export default {
    name: "IndexMore",
    props:{
        path:{
            type: String,
            default: "/"
        }
    }
}
</script>

<style lang="less">
.IndexMore{
    margin-bottom: 3px;
    span {
        font-size: 15px;
        vertical-align: middle;
        color:silver;
        margin-right: 0px;
    }
}
</style>