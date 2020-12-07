import { BlockElement, BlockCreate } from "@/models/BlockChainTypes"
import axios, {AxiosInstance} from 'axios'
import { UrlConstants} from "@/constants/Constants"
import { BaseMessage } from "@/models/intermediatedtos"

export class HttpService {
    private baseurl: string;
    protected readonly instance: AxiosInstance;
    constructor(baseurl: string) {
        this.baseurl = baseurl;
        this.instance = axios.create();
    }

    addBlock(payload: BlockCreate): Promise<BaseMessage> {
    /* Make post request to the server */
    console.log("url " + this.baseurl + UrlConstants.add);
    Promise.resolve(1).then( i => {
        console.log(i);
        return i + 1 ;    
    }).then(res => {
        console.log(res);
        return res + 1;
    });
    const addReq = axios.post(this.baseurl + UrlConstants.add , payload);

    return axios.all([addReq]).then(axios.spread((...responses) =>{
        const addResp = responses[0];
        const addRespData = addResp.data;
        return new BaseMessage(addRespData.success, addRespData.message, "");
    })).catch(error => {
        return new BaseMessage(false, error.message);
    })
    }

    getAll (): any {
        console.log("getall");
        const endpoint = this.baseurl+ UrlConstants.all;
        return this.instance.get<BlockElement[]>(endpoint).then((response) => {
          const result = response.data;
          return new BaseMessage(true, "Fetched All Blocks : Total Received: " + result.length, response.statusText,result);
        }).catch(error => {                
            console.log("Error Fetching All Blocks...");
            return new BaseMessage(false, error.message);
        });
    }

    update(data: string, id: string, attempts: number)  {
        console.log("Update Service Called...");
        const payload = { "data": data, "attempts": attempts };

        console.log("Update Request url: ", this.baseurl + UrlConstants.update);
        const updatePromose = this.instance.put(this.baseurl + UrlConstants.update + id, payload);
        return axios.all([updatePromose] ).then(axios.spread((...response) => {
            const updateData = response[0].data;
            if (updateData.success) {
                console.log(updateData.message)
                return new BaseMessage(true, updateData.message);
            } else {
                return new BaseMessage(false, updateData.message);
            }
        })).catch(error => {
            return new BaseMessage(false, error.message);
        });
    }

    clearBlockchain(): Promise<BaseMessage> {
        const endpoint = this.baseurl+ UrlConstants.clear;
        return this.instance.delete(endpoint).then((response) => {
          const result = response.data;
          return new BaseMessage(true, "Fetched All Blocks : Total Received: " + result.length, response.statusText,result);
        }).catch(error => {
            console.log("Error Fetching All Blocks...");
            return new BaseMessage(false, error.message);
        });
    }
}
