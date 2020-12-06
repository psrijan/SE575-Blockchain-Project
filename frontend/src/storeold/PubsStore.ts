import {VuexModule, Module, Mutation, Action}from 'vuex-module-decorators'
import {CourseType} from './CourseTypes'
import axios, { AxiosResponse, AxiosError } from "axios";

@Module({name: 'pubs'})
export default class PubStoreModule extends VuexModule{
    private _courseList: CourseType[] = [];
    private _cacheOK = false

    get courseList(){
        return this._courseList
    }

    get cacheOK(){
        return this._cacheOK
    }

    get course(): (id: number) => CourseType|null  {
        return (id) => {
            if (id < 0 || id > this._courseList.length || !this.cacheOK)
                return null
            else
                return {...this._courseList[id]}
        }
    }   

    @Mutation
    cacheStatus(status: boolean){
        this._cacheOK = status
    }

    @Mutation
    addCourse(c: CourseType){
        this._courseList = [...this._courseList, {...c}]
    }

    @Mutation
    loadAllCourses(cList: CourseType[]){
        this._courseList = [...cList];
    }

    @Mutation
    updateCourse(id: number, c: CourseType){
        if (id >= 0 || id < this._courseList.length) {
            this._courseList = [...this._courseList]
            this._courseList[id] = {...c}
        }
    }

    @Action
    async loadCache(endpoint: string) {
        
      return axios.get<CourseType[]>(endpoint).then((response) => {
        const result = response.data;
        console.log('axios action ',result);
        this.context.commit('loadAllCourses', result)
        this.context.commit('cacheStatus',true)
      });
   }
}