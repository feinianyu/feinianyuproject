package com.example.demo.entity;

public class ChkPay {
    private String jrn_no;
    private String tx_typ;
    private String rvs_flg;
    private String tx_amt;

    public void setJrn_no(String jrn_no) {
        this.jrn_no = jrn_no;
    }

    public void setTx_typ(String tx_typ) {
        this.tx_typ = tx_typ;
    }

    public void setRvs_flg(String rvs_flg) {
        this.rvs_flg = rvs_flg;
    }

    public void setTx_amt(String tx_amt) {
        this.tx_amt = tx_amt;
    }
    public String getTx_typ() {
        return tx_typ;
    }
    public String getJrn_no() {
        return jrn_no;
    }

    public String getRvs_flg() {
        return rvs_flg;
    }

    public String getTx_amt() {
        return tx_amt;
    }
}
