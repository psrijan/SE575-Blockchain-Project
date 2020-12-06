import { FormSelectPlugin } from 'bootstrap-vue';
import {VuexModule, Module, Mutation, Action}from 'vuex-module-decorators'
import {CourseType} from './CourseTypes'

@Module({stateFactory: true, name: 'simpleCounter'})
export default class SimpleStoreModule extends VuexModule{
    private _counter = 0;

    get counter(){
        console.log('in counter getter')
        return this._counter
    }

    @Mutation
    adjustCounter(x: number){
        console.log('in adjustr counter ', x)
        this._counter += x;
    }

    @Action({commit: 'adjustCounter'})
    async adjustCounterAction(x: number){
        //async stuff goes here
        return x
        //decorator drives same as this.context.commit('adjustCounter', x)
    }
    
    @Action
     async adjustCounterDelayAction(args: {delay: number; x: number}) {
        console.log('in delay action ',args.delay, ' xval: ', args.x)

        return new Promise( resolve => setTimeout( () => {
            this.context.commit('adjustCounter',args.x)
        }, args.delay))
    }
}