import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AgGridAngular } from 'ag-grid-angular';
import { CellValueChangedEvent } from 'ag-grid-community';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
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

  getSelectedRows() {
    const selectedNodes = this.agGrid?.api.getSelectedNodes();
    const selectedData = selectedNodes?.map(node => node.data);
   /* const selectedDataStringPresentation = selectedData?.map(node => node.id + "..." + node.student)
      .join(", ");
    alert(`Selected Nodes: ${selectedDataStringPresentation}`)*/
    this._http.post<any>('http://localhost:8081/summarys/writeAComment.do', selectedData/*JSON.stringify(selectedData),  {headers: {'Content-Type':'application/json'}}*/)
    .subscribe((data) => {
      this.tutorComment= data.comment;
    });
  }

  onCellValueChanged(params: CellValueChangedEvent){
    console.log("Updating ...")
    const updatedSummary = params.data;
    this._http.post<any>('http://localhost:8081/summarys/updateSummary.do',updatedSummary)
      .subscribe((data)=>{
        console.log(updatedSummary);
        if(data.msg=="SUCCESS") alert('Update is successful ...')
        else alert('Update has failed ...')
      })
  }

}

