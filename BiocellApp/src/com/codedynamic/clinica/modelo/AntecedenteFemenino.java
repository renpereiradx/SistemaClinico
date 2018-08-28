package com.codedynamic.clinica.modelo;

public class AntecedenteFemenino {
	private String edadMenarca;
    private String edadClimaterio;
    private String menopausia;
    private String fum;
    private String terapiaAnticonceptivo;
    private String inicioSexual;
    private String nroPareja;
    private String cirugiaPelvania;
    private String pap;
    private String fecha;
    private String mamografia;
    private String est;
    private Paciente paciente;

    public AntecedenteFemenino() {
        this(null, null, null, null, null, null, null,
                null, null, null, null, null, null);
    }

    public AntecedenteFemenino(String edadMenarca, String edadClimaterio, String menopausia, String fum, String terapiaAnticonceptivo,
                               String inicioSexual, String nroPareja, String cirugiaPelvania, String pap, String fecha, String mamografia, String est, Paciente paciente) {
        this.edadMenarca = edadMenarca;
        this.edadClimaterio = edadClimaterio;
        this.menopausia = menopausia;
        this.fum = fum;
        this.terapiaAnticonceptivo = terapiaAnticonceptivo;
        this.inicioSexual = inicioSexual;
        this.nroPareja = nroPareja;
        this.cirugiaPelvania = cirugiaPelvania;
        this.pap = pap;
        this.fecha = fecha;
        this.mamografia = mamografia;
        this.est = est;
        this.paciente = paciente;
    }

    public String getEdadMenarca() {
        return edadMenarca;
    }

    public void setEdadMenarca(String edadMenarca) {
        this.edadMenarca = edadMenarca;
    }

    public String getEdadClimaterio() {
        return edadClimaterio;
    }

    public void setEdadClimaterio(String edadClimaterio) {
        this.edadClimaterio = edadClimaterio;
    }

    public String getMenopausia() {
        return menopausia;
    }

    public void setMenopausia(String menopausia) {
        this.menopausia = menopausia;
    }

    public String getFum() {
        return fum;
    }

    public void setFum(String fum) {
        this.fum = fum;
    }

    public String getTerapiaAnticonceptivo() {
        return terapiaAnticonceptivo;
    }

    public void setTerapiaAnticonceptivo(String terapiaAnticonceptivo) {
        this.terapiaAnticonceptivo = terapiaAnticonceptivo;
    }

    public String getInicioSexual() {
        return inicioSexual;
    }

    public void setInicioSexual(String inicioSexual) {
        this.inicioSexual = inicioSexual;
    }

    public String getNroPareja() {
        return nroPareja;
    }

    public void setNroPareja(String nroPareja) {
        this.nroPareja = nroPareja;
    }

    public String getCirugiaPelvania() {
        return cirugiaPelvania;
    }

    public void setCirugiaPelvania(String cirugiaPelvania) {
        this.cirugiaPelvania = cirugiaPelvania;
    }

    public String getPap() {
        return pap;
    }

    public void setPap(String pap) {
        this.pap = pap;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMamografia() {
        return mamografia;
    }

    public void setMamografia(String mamografia) {
        this.mamografia = mamografia;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
