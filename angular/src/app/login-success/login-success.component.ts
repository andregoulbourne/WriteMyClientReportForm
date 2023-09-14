import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient, HttpParams} from '@angular/common/http';
import { AgGridAngular } from 'ag-grid-angular';
import { CellValueChangedEvent } from 'ag-grid-community';

@Component({
  selector: 'app-login-success',
  templateUrl: './login-success.component.html',
  styleUrls: ['./login-success.component.css']
})
export class LoginSuccessComponent {
  @ViewChild('agGrid', { static: false }) agGrid: AgGridAngular | undefined;
  title = 'WriteClientReport';

  colDef = [
    { headerName: "Id", field: "id", sortable: true, filter: true, checkboxSelection: true},
    { headerName: "Name", field: "student", sortable: true, filter: true, editable: true},
    { headerName: "Status", field: "status", sortable: true, filter: true, editable: true },
    { headerName: "Improvement", field: "madeADifference", sortable: true, filter: true, editable: true },
    { headerName: "Covered", field: "coveredValue", sortable: true, filter: true, editable: true },
    { headerName: "Recomendation", field: "recomendation", sortable: true, filter: true, editable: true },
    { headerName: "Gender", field: "gender", sortable: true, filter: true, editable: true },
  ]

  rowData: any;

  constructor(private _http: HttpClient) { }

  ngOnInit() {
    this._http.get<any>('http://localhost:8081/summarys')
      .subscribe((data) => {
        this.rowData = data.data;
      });
  }

  tutorComment:any;

  writeComment() {
    let selectedData = this.getSelectedSummary();
    
    this._http.post<any>('http://localhost:8081/summarys/writeAComment.do', selectedData/*JSON.stringify(selectedData),  {headers: {'Content-Type':'application/json'}}*/)
    .subscribe((data) => {
      this.tutorComment= data.comment;
    });
  }
  
  private getSelectedSummary() {
	const selectedNodes = this.agGrid?.api.getSelectedNodes();
    const selectedData = selectedNodes?.map(node => node.data);
    
    return selectedData;
  }

  updateSummary(params: CellValueChangedEvent){
    console.log("Updating ...")
    const updatedSummary = params.data;
    this._http.post<any>('http://localhost:8081/summarys/updateSummary.do',updatedSummary)
      .subscribe((data)=>{
        if(data.msg=="SUCCESS") alert('Update is successful ...')
        else alert('Update has failed ...')
      })
  }
  
  addDefaultInitializedSummary(){
	console.log("Adding Default Summary ...")
	const summary = {id: this.getMaxIdNumber()+1, student: '', status: '', madeADifference: true, coveredValue: '', recomendation: '', gender:'F'};
	
	this._http.post<any>('http://localhost:8081/summarys',summary)
      .subscribe((data)=>{
        if(data.msg=="SUCCESS"){
			alert('Insert is successful ...')
			this.ngOnInit();
        } else alert('Insert has failed ...')
      })
  }
  
  private getMaxIdNumber(){
	let maxId = 0;
	for (let summary of this.rowData) {
	  let currentId = parseInt(summary.id);
	  
	  if(currentId > maxId) 
	  	maxId = currentId; 
	}
	
	return maxId;
  }
  
  deleteSummary(){
	console.log("Deleting Summary ...")
	
	let selectedData = this.getSelectedSummary();
	
	selectedData?.forEach( (summary: any) => {
		let httpParams = new HttpParams();
		httpParams = httpParams.set('id', summary.id);
		this._http.post<any>('http://localhost:8081/summarys/deleteSummary.do', httpParams)
	      .subscribe((data)=>{
	        if(data.msg =="SUCCESS"){
				alert('Delete is successful ...')
				this.ngOnInit();
	        } else alert('Delete has failed ...')
	      })
    });
  }

}

