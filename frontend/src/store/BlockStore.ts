import Vue from 'vue'
import Vuex from 'vuex'
import {BlockElement} from '@/models/BlockChainTypes'
Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        blocks: Array<BlockElement>(),
        counter: 0 
    },

    mutations: {
        setBlocks(state, blocks: Array<BlockElement>) {
            state.blocks = blocks
        },

        setCounter(state, count: number ) {
                state.counter = count;
        }
    },

    actions: {
        setCounter(context, count: number) {
            context.commit("setCounter", count);
        }
    }

});