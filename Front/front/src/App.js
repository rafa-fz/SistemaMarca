import React, { useEffect, useState } from "react";
import axios from "axios";
import "./App.css"; // Asegúrate de tener el archivo de estilos

function App() {
    const [transacciones, setTransacciones] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        // Llama al endpoint para obtener todas las transacciones
        axios.get("http://localhost:8080/transaccion")
            .then(response => {
                const datosFiltrados = response.data.map(item => ({
                    fechaTransaccion: item.fechaTransaccion,
                    monto: item.monto,
                    estado: item.estado,
                    codigoAutorizacion: item.codigoAutorizacion,
                    paisOrigen: item.paisOrigen,
                    comisionMarca: item.comisionMarca
                }));
                setTransacciones(datosFiltrados);
            })
            .catch(error => {
                console.error("Error al obtener las transacciones:", error);
                setError("Error al cargar los datos. Intente nuevamente más tarde.");
            })
            .finally(() => {
                setLoading(false);
            });
    }, []);

    return (
        <div className="container">
            <h1 className="title">Reporte</h1>
            
            {/* Manejo del estado de carga y errores */}
            {loading ? (
                <p className="loading">Cargando transacciones...</p>
            ) : error ? (
                <p className="error">{error}</p>
            ) : (
                <div className="report-container">
                    {transacciones.length > 0 ? (
                        transacciones.map((transaccion, index) => (
                            <div key={index} className="transaction-report">
                                <h2>Transacción #{index + 1}</h2>
                                <p><strong>Fecha:</strong> {transaccion.fechaTransaccion}</p>
                                <p><strong>Monto:</strong> ${transaccion.monto.toFixed(2)}</p>
                                <p><strong>Estado:</strong> {transaccion.estado}</p>
                                <p><strong>Código de Autorización:</strong> {transaccion.codigoAutorizacion}</p>
                                <p><strong>País de Origen:</strong> {transaccion.paisOrigen}</p>
                                <p><strong>Comisión de Marca:</strong> ${transaccion.comisionMarca?.toFixed(2) || "N/A"}</p>
                                <hr />
                            </div>
                        ))
                    ) : (
                        <p className="no-data">No hay datos disponibles</p>
                    )}
                </div>
            )}
        </div>
    );
}

export default App;
