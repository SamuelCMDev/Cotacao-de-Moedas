import { MoedaDados } from "../types/Moeda";

interface CardMoedaProps {
     dados: MoedaDados;
}

export function CardMoeda({ dados }: CardMoedaProps) {
    const ehPositivo = dados.variacao > 0;

    return (
        <div className="bg-white rounded-3xl p-8 shadow-2xl border border-slate-100 w-full  max-w-sm mx-auto transition-all hover:translate-y-[-5px]">
            <h3 className="text-slate-400 uppercase tracking-widest text-xs font-bold mb-2">
                Resultado da Busca
            </h3>
            
            <h2 className="text-4xl font-black text-slate-800 mb-6 tracking-tighter">
                {dados.moeda}
            </h2>

            <div className="space-y-1">
                <p className="text-gray-500 text-sm font-medium">Valor Atual</p>
                <span className="text-3xl font-bold text-slate-900">
                    R$ {dados.valor.toFixed(2)}
                </span>
            </div>

            <div className="mt-6 flex items-center gap-3">
                <span className={`px-4 py-1.5 rounded-full text-sm font-black ${
                    ehPositivo ? 'bg-green-100 text-green-600' : 'bg-red-100 text-red-600'
                }`}>
                    {ehPositivo ? '▲' : '▼'} {dados.variacao.toFixed(2)}
                </span>
                <span className="text-slate-400 text-xs font-medium">Variação 24h</span>
            </div>
        </div>
    );
}
