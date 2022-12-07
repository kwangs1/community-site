package com.spring.community.common;

//�Խñ� ��ȸ ������ ���޵� �Ķ���͸� ��� �� Ŭ����
public class Criteria {
	private int page;//���� ������ ��ȣ
	private int perPageNum;//�� �������� ������ �Խñ� ����
	private int rowStart;//�Խñ� �������ȣ
	private int rowEnd;//�Խñ� ���������ȣ


	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		//���� ������ ��ȣ�� 0���� ������ 1�� ǥ��
		if(page <=0) {
			this.page = 1;
		}else {
			this.page = page;			
		}
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int PageCount) {
		int cnt = this .perPageNum;
		if(PageCount != cnt) {
			this.perPageNum = cnt;
		}else {
			this.perPageNum = PageCount;			
		}
	}
	
	//���� ��ȣ
	public int getRowStart() {
		//(���� ��������ȣ -1) * �� �������� ������ �Խñ� ����
		rowStart = ((page - 1) * perPageNum) + 1;
		return rowStart;
	}
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	//�� ��ȣ
	public int getRowEnd() {
		rowEnd = rowStart + perPageNum - 1;
		return rowEnd;
	}

	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}
	
	
}
