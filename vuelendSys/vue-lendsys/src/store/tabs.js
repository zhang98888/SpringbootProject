import { createStore } from 'vuex'

const store = createStore({
  state: {
    isCollapse: false,
    currentMenu: null,
    tabList:[
        {
          path: '/admin',
          name: 'Home',
          icon: 'menu',
          label: 'Home',
        },
    ]
  },
  mutations: {
      collapseMenu(state){
          state.isCollapse = !state.isCollapse
      },
      selectMenu(state,val) {
        

          if(val.name === 'Home'){
            state.currentMenu = null
          }else{
            state.currentMenu = val
            let result = state.tabList.findIndex(item=>item.name == val.name)
            result === -1? state.tabList.push(val):''
          }
      },
      closeTag(state,val){
        let result = state.tabList.findIndex(item => item.name === val.name)
        state.tabList.splice(result,1)
      }
  },
  actions: {
  },
  modules: {
  }
})


export default store