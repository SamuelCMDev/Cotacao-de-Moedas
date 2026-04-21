import React, { useState } from 'react'; 
import { CardMoeda } from './components/CardMoeda';
import { MoedaDados, MoedaHistorico } from './types/Moeda';
import './App.css'
import { GrafMoedas } from './components/GraficoMoeda';

export default function App() {
    const [cotacao, setCotacao] = useState<MoedaDados | null>(null);
    const [historico, setHistorico] = useState<MoedaHistorico[]>([]);

    async function buscarDados(sigla: string) {
        const response = await fetch(`http://localhost:8080/api/cotacao/${sigla}`);
        const dadosConvertidos: MoedaDados = await response.json();
        setCotacao(dadosConvertidos);
        
        const resHist = await fetch(`http://localhost:8080/api/historico/${sigla}`);
        const dadosHistorico = await resHist.json();
        setHistorico(dadosHistorico);
    }

    return (
        <div className="flex min-h-screen bg-slate-900 text-white">
            {/* Sidebar Fixa na Esquerda */}
            <aside className="w-64 bg-slate-800 border-r border-slate-700 flex flex-col p-6 gap-4">
                <h2 className="text-sm font-bold text-slate-500 uppercase tracking-widest mb-4">Moedas</h2>
                <button onClick={() => buscarDados("USD")} className="bg-white text-blue-600 font-semibold py-2 px-4 rounded-lg">Dólar (USD)</button>
                <button onClick={() => buscarDados("BTC")} className="bg-white text-orange-600 font-semibold py-2 px-4 rounded-lg">Bitcoin (BTC)</button>
                <button onClick={() => buscarDados("EUR")} className="bg-white text-emerald-600 font-semibold py-2 px-4 rounded-lg">Euro (EUR)</button>
            </aside>

            {/* Área Principal */}
            <main className="flex-1 p-10">
                <h1 className="text-4xl font-extrabold text-slate-400 mb-10 text-center">
                    Monitor de <span className="text-blue-200 font-black">Cotações</span>
                </h1>
                
                {/* Container Lado a Lado sem quebra responsiva */}
                <div className="flex flex-row items-stretch gap-6 w-full max-w-7xl mx-auto h-[450px]">
                    
                    {/* Coluna do Card (Esquerda) */}
                    <div className="flex-none w-[350px] bg-slate-800/30 rounded-3xl border border-slate-700/50 p-6 flex items-center justify-center">
                        {cotacao ? (
                            <CardMoeda dados={cotacao} />
                        ) : (
                            <p className="text-slate-600 italic text-center">Selecione uma moeda no menu</p>
                        )}
                    </div>

                    {/* Coluna do Gráfico (Direita) */}
                    <div className="flex-1 bg-slate-800/50 rounded-3xl border border-slate-700 p-4">
                        {historico.length > 0 ? (
                            <GrafMoedas dados={historico} />
                        ) : (
                            <div className="h-full flex items-center justify-center text-slate-600">Aguardando dados...</div>
                        )}
                    </div>
                </div>
            </main>
        </div>
    );
}