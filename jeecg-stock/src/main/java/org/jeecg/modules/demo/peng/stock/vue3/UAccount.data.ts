import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '名称',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '代码',
    align:"center",
    dataIndex: 'code'
   },
   {
    title: '日期',
    align:"center",
    dataIndex: 'date',
    customRender:({text}) =>{
      return !text?"":(text.length>10?text.substr(0,10):text)
    },
   },
   {
    title: '操作',
    align:"center",
    dataIndex: 'opration'
   },
   {
    title: '单价',
    align:"center",
    dataIndex: 'price'
   },
   {
    title: '数量',
    align:"center",
    dataIndex: 'amount'
   },
   {
    title: '均价',
    align:"center",
    dataIndex: 'averageprice'
   },
   {
    title: '收益',
    align:"center",
    dataIndex: 'revenue'
   },
   {
    title: '在仓',
    align:"center",
    dataIndex: 'inrepository'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "代码",
      field: 'code',
      component: 'Input',
      colProps: {span: 6},
 	},
     {
      label: "日期",
      field: "date",
      component: 'RangePicker',
      colProps: {span: 6},
	},
	{
      label: "在仓",
      field: 'inrepository',
      component: 'Input',
      colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '名称',
    field: 'name',
    component: 'Input',
  },
  {
    label: '代码',
    field: 'code',
    component: 'Input',
  },
  {
    label: '日期',
    field: 'date',
    component: 'DatePicker',
  },
  {
    label: '操作',
    field: 'opration',
    component: 'Input',
  },
  {
    label: '单价',
    field: 'price',
    component: 'InputNumber',
  },
  {
    label: '数量',
    field: 'amount',
    component: 'InputNumber',
  },
  {
    label: '均价',
    field: 'averageprice',
    component: 'InputNumber',
  },
  {
    label: '收益',
    field: 'revenue',
    component: 'InputNumber',
  },
  {
    label: '在仓',
    field: 'inrepository',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];



/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}